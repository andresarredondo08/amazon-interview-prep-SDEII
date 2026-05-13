import java.util.*;

public class Main {
    public static void main(String[] args) {
        /*Longest Substring Without Repeating Characters
        Enunciado
        Dado un string s, encuentra la longitud del substring más largo sin caracteres repetidos.
        Input: "abcabcbb"
        Output: 3

        Explanation:
        "abc" es el substring más largo sin repetir.
        */
        longestSubstringWithoutRepeatingCharacters("abcabccc");

        /*
        Two Sum
        Enunciado

        Dado un array nums y un número target, retorna los índices de dos números que sumen target.
        Input: nums = [2,7,11,15], target = 9
        Output: [0,1]
        Porque:

        nums[0] + nums[1] = 2 + 7 = 9
         */
        int[] numsTwoSum = new int[]{2,7,11,15};
        int targetTwoSum = 9;
        twoSum(numsTwoSum, targetTwoSum);

        /*
        Top K Frequent Elements
        Enunciado
        Dado un array nums y un entero k, retorna los k elementos más frecuentes.

        Ejemplo
        Input:
        nums = [1,1,1,2,2,3]
        k = 2

        Output:
        [1,2]
        Porque:

        1 aparece 3 veces
        2 aparece 2 veces

         */
        int[] numsTopKFrequent = new int[]{1,1,1,2,2,3};
        int frequentKElements = 2;

        topKFrequentElements(numsTopKFrequent,frequentKElements);

        /*
        Numbers of Island

        Dada una matriz grid de '1' y '0':

        '1' = tierra
        '0' = agua

        Retorna cuántas islas hay.

        Una isla está conectada horizontal o verticalmente, no diagonalmente.

        Input:
        [
          ['1','1','0','0'],
          ['1','0','0','1'],
          ['0','0','1','1']
        ]

        Output: 2
        */
        int[][] grid= {
            {1,1,0,0},
            {1,0,0,1},
            {0,0,1,1}
        };
        numbersOfIsland(grid);

        /*
        Binary Tree Level Order Trasversal

        Enunciado

        Dado el root de un árbol binario, retorna los nodos:
        nivel por nivel

        Ejemplo

        Árbol:

                3
              /   \
             9     20
                  /  \
                 15   7

        Output:

        [
          [3],
          [9,20],
          [15,7]
        ]
         */

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode( 20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);


        binaryTreeLevelOrderTrasversal(root);

    }

    static int longestSubstringWithoutRepeatingCharacters(String s){
        int max = 0;
        int left = 0;
        Set<Character> window = new HashSet<>();

        for(int right = 0; right< s.length(); right++){
            char current = s.charAt(right);
            while(window.contains(current)){
                window.remove(s.charAt(left));
                left++;
            }
            window.add(current);
            max = Math.max(max,right-left+1);
        }
        return max;
    }

    static int[] twoSum(int[] nums, int target){

        Map<Integer, Integer> indexByNumber = new HashMap<>();
        for(int i = 0;i<nums.length;i++){
            int complement = target - nums[i];
            if(indexByNumber.containsKey(complement)){
                return new int[]{indexByNumber.get(complement),i};
            }
            indexByNumber.put(nums[i],i);
        }
        return new int[]{};
    }

    static int[] topKFrequentElements(int[] nums, int k){
        Map<Integer, Integer> frequency = new HashMap<>();
        for(int i = 0 ; i<nums.length; i++){
            frequency.merge(nums[i],1,Integer::sum);
        }
        PriorityQueue<Integer> minHeap =
                new PriorityQueue<>(
                        (a,b) -> frequency.get(a) - frequency.get(b)
                );

        for(int num : frequency.keySet()){
            minHeap.offer(num);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        int[] result = new int[k];

        for (int i=k;i<minHeap.size();i++){
            result[i]=minHeap.poll();
        }

        return result;
    }

    static int numbersOfIsland(int[][] grid){
        int numbersOfIsland = 0;

        for(int i = 0; i <grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == 1 ){
                    numbersOfIsland++;
                    callDFS(grid,i,j);
                }
            }
        }

        return numbersOfIsland;
    }

    static void callDFS(int[][] grid,int i, int j){
        if(i<0 || i >= grid.length || j<0 || j >= grid[i].length || grid[i][j] == 0 ){
            return;
        }
        grid[i][j] = 0;
        callDFS(grid,i+1,j); //Down
        callDFS(grid,i-1,j); //Up
        callDFS(grid,i,j+1); // Right
        callDFS(grid,i,j-1); // Left
    }

    static List<List<Integer>> binaryTreeLevelOrderTrasversal(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        return result;
    }

    static class TreeNode{
        int val;

        TreeNode right;
        TreeNode left;
        TreeNode(int val){
            this.val = val;
        }
    }

}
