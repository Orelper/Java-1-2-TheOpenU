/**
 * Maman 13 OpenU
 * This class is included all the questions from Maman 13
 * @version (2023a)
 * @author Orel Perez
 */

public class Ex13 
{
    /** Question 1 :
     * This method is getting a string filled with the chars 0 and 1 and returns
     * the minimum number of required swaps to get an alternating sequence of bits (0101.../1010...).
     * We can assume that there is an exact number of 0's and 1's.
     * @param s String to check.
     * @return Minimum number of swaps to get an alternating sequence of bits.
     * Time complexity - O(n).
     * Space complexity - O(1).
     */
    public static int alternating(String s)
    {
        int flipsToStartWith1 = 0;
        int flipsToStartWith0 = 0;

        for (int i = 0; i < s.length(); i = i + 2)
        {
            if (s.charAt(i) == '1') //counts the swaps required to start with '0'.
            {
                flipsToStartWith0++;
            }

            else //counts the swaps required to start with '1'.
            {
                flipsToStartWith1++;
            }
        }

        return (Math.min(flipsToStartWith0, flipsToStartWith1)); //checks if its better to start with '0'/'1'.
    }


    /** Question 2 :
     * S1- The method what getting an array full with numbers and returns the largest subarray with even sum.
     * S2- Time complexity - O(n^3).  
     *    Space complexity - O(1).  
     * S4- My method time complexity - O(n).  
     *    My method Space complexity - O(1).	
     * @param a int array.
     * @return the largest subarray with even sum.
     */
    public static int what(int[] a)
    {
        int res = 0;
        int tempOdd = -1;
        int finalOdd = -1;

        for (int i = 0; i < a.length; i++)
        {
            if (a[i] % 2 == 1 || a[i] % 2 == -1)
            {
                if (tempOdd < 0)
                {
                    tempOdd = i;
                }

                finalOdd = i;
   
            }
            res += a[i];
        }

        if (res % 2 == 0)
        {
            return a.length;
        }

        int firstbiggest = a.length - (tempOdd + 1);
        
        int lastbiggest = finalOdd;

        return Math.max(firstbiggest, lastbiggest);
    }


    /** Question 3 :
     * This method getting an array filled with positive numbers from type int
     * and checks if theres a valid path from the first index to the last one.
     * Valid path is a path taking steps in array left or right according to 
     * the value in index without leaving the boundaries of the array.
     * @param a int array.
     * @return true if there's a valid path / false if there isn't.
     */
    public static boolean isWay(int[] a)
    {
        return isWay(a, 0);
    }

    private static boolean isWay(int[] a, int i)
    {
        if (i == a.length - 1)//stop condition
        {
            return true;
        }

        if (i < 0 || i >= a.length || a[i] < 0)//stop condition
        {
            return false;
        }

        int temp = a[i]; //save value
        a[i] = -1; //mark
        boolean toTheRight = isWay(a, i + temp); //recursive check of right path.
        boolean toTheLeft = isWay(a, i - temp); //recursive check of left path.
        
        a[i] = temp; //return saved value

        return toTheRight || toTheLeft;
    }

    /** Question 4 :
     * This method returns the shortest valid path in matrix from prince to evil (valued -1 in matrix) so he could save the princess,
     * a valid path of prince to move in matrix is up, down, left and right, also the maximum value of an index that the prince could go to
     * if its a bigger one is by 1 or smaller one by 2 from value of the index he stands on.
     * @param drm two dimensional array (matrix).
     * @param i integer.
     * @param j integer.
     * @return the number of shortset path in matrix from prince to evil / -1 if there is no valid path.
     */
    public static int prince(int[][] drm, int i, int j) 
    {
        int before = drm[i][j]; //the previous cell
        return prince(drm, i, j, before);
    }

    private static int prince(int[][] drm, int i, int j, int before)
    {
        if (i < 0 || i >= drm.length || j < 0 || j >= drm[i].length) //stop conditions
        {
            return -1;
        }

        if (drm[i][j] == -1) //stop condition
        {
            return 1;
        }

        if (drm[i][j] == -5) //check if marked
        {
            return -1;
        }

        if (drm[i][j] > before + 1) //stop condition
        {
            return -1;
        }
        
        if (drm[i][j] < before - 2) //stop condition
        {
            return -1;
        }
        

        int temp = drm[i][j]; //save value
        drm[i][j] = -5; //mark

        int checkUp = prince(drm, i - 1, j, temp); //move up (if possible)
        int checkDown = prince(drm, i + 1, j, temp); //move down (if possible)
        int checkLeft = prince(drm, i, j - 1, temp); //move left (if possible)
        int checkRight = prince(drm, i, j + 1, temp); //move right (if possible)

        drm[i][j] = temp; //return saved value

        int res = minPrince(minPrince(checkLeft, checkRight), minPrince(checkUp, checkDown));
        
        if (res == -1)
        {
            return res;
        }
        else
        {
            return res + 1;
        }   
    }

    private static int minPrince(int min1,int min2) //aesthetic minimum method.
    {
        if (Math.min(min1, min2) > -1)
        {
            return Math.min(min1, min2);
        }
        else
        {
            return Math.max(min1, min2);
        }
    }
}
