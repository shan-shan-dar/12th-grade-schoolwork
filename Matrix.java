import java.util.Scanner;

public class Matrix
{
    /**
     * input asks the user to enter the size of the matrix and its elements
     */
    
    public double[][] input()
    {
        Scanner sc = new Scanner(System.in);
        Matrix obj = new Matrix();
        
        System.out.println("Rows: ");
        int r = sc.nextInt();
        System.out.println();
        System.out.println("Columns: ");
        int c = sc.nextInt();
        System.out.println();
        
        double[][] tab = new double[r][c];
        
        for(int i = 0; i < r; i++)
        {
            for(int j = 0; j < c; j++)
            {
                System.out.println("[" + i + "][" + j + "]");
                double k = sc.nextDouble();
                tab[i][j] = k;
            }
        }
        
        System.out.println();
        obj.print(tab);
        System.out.println();
        
        return tab;
    }
    
    /**
     * print prints the contents of the desired matrix in the proper format
     */
    public void print(double[][] tab)
    {
        System.out.print("{");
        for (int row=0; row < tab.length; row += 1)
        {   
            System.out.print("{");
            for (int col = 0; col < tab[row].length ; col += 1)
            {
                System.out.print(tab[row][col]);
                if (!(col  == tab[row].length - 1))
                {
                    System.out.print(", ");
                }    
            }
            System.out.print("}");
            if (row  != tab.length - 1)
            {
                System.out.println(", ");
            }    
            
        }
        System.out.print("}");
    }
    
    /**
     * transposé returns another matrix that is the transposed version of the given matrix 
     */
    public double[][] transposé(double[][] tab)
    {
        double[][] transposéd = new double[tab[0].length][tab.length];
        
        for(int ro = 0; ro < transposéd.length; ro++)
        {
            for (int col = 0; col < transposéd[ro].length; col++)
            {
                transposéd[ro][col] = tab[col][ro];
            }
        }
        
        print(transposéd);
        return transposéd;
    }
    
    /**
     * transpose2 returns alters and returns the same matrix as its transposed version
     */
    public void transpose2(double[][] tab)
    {   
        if (tab.length != tab[0].length)
        {
            System.out.println("Invalid size. Use Transposé(1)");
            return;
        }
        
        double[][] temp = new double[tab.length][tab.length];
        
        for(int ro = 0; ro < temp.length; ro++)
        {
            for (int col = 0; col < temp[ro].length; col++)
            {
                temp[ro][col] = tab[ro][col];
            }
        }
        
        for(int ro = 0; ro < temp.length; ro++)
        {
            for (int col = 0; col < temp[ro].length; col++)
            {
                tab[ro][col] = temp[col][ro];
            }
        }
        print(tab);
    }
    
    /**
     * addDiag adds all the diagonal elements of a square matrix without repetition
     */        
    public double addDiag(double[][] tab)
    {
        if(tab.length != tab[0].length)
        {
            System.out.println("Invalid size");
            return 0;
        }
        
        double ans1 = 0;
        double ans2 = 0;
        
        for(int i = 0; i < tab.length; i++)
        {
            ans1 = ans1 + tab[i][i];
        }
        
        for(int j = 0; j < tab.length; j++)
        {
            if (j != (tab.length - 1) - j)
            {
                ans2 = ans2 + tab[j][(tab.length - 1) - j];
            }
        }
        
        return ans1 + ans2;
    }
    
    /**
     * rotate rotates the elements of a given matrix based on the desired level
     * level 1 only rotates the outer edges of the matrix
     * level 2 rotates the outer edge and the second most outer edge
     * etc.
     */
    public double[][] rotate(double[][] tab, int level)
    {
        double[][] rotated = new double[tab.length][tab[0].length];
        
        for(int ro = 0; ro < rotated.length; ro++)
        {
            for (int col = 0; col < rotated[ro].length; col++)
            {
                rotated[ro][col] = tab[ro][col];
            }
        }
        
        for(int l = 0; l < level; l++)
        {
            //top edge
            for(int i = 0 + l; i < tab[0].length - 1 - l; i++)
            {
                rotated[0 + l][i+1] = tab[0 + l][i];
            }
            
            //right edge
            for(int i = 0 + l; i < tab.length - 1 - l; i++)
            {
            rotated[i+1][tab[0].length - 1 - l] = tab[i][tab[0].length - 1 - l];
            }
            
            //bottom edge
            for(int i = tab[0].length - 1 - l; i > 0 + l; i--)
            {
                rotated[tab.length - 1 - l][i-1] = tab[tab.length - 1 - l][i];
            }
            
            //left edge
            for(int i = tab.length - 1 - l; i > 0 + l; i--)
            {
                rotated[i-1][0 + l] = tab[i][0 + l];
            }
        }
        
        print(rotated);
        return rotated;
    }
    
    /**
     * mult simply performs matrix multiplication and returns the product matrix
     */
    public double[][] mult(double[][] tab1, double[][] tab2)
    {
        double[][] ans = new double[tab1.length][tab2[0].length];
        
        for(int i = 0; i < tab1.length; i++)
        {
            for(int j = 0; j < tab2[0].length; j++)
            {
                double temp = 0;
                
                for(int k = 0; k < tab1[0].length; k++)
                {
                    temp = temp + (tab1[i][k] * tab2[k][j]);
                }
                
                ans[i][j] = temp;
            }
        }
        
        return ans;
    }
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Matrix obj = new Matrix();
        
        int option = 1;
        double[][] tab;
        
        System.out.println("0. Exit\n1. Transpose\n2. Transpose2\n3. Add Diagonals\n4. Rotate\n5. Multiply\n");
        option = sc.nextInt();
        
        while(option != 0)
        {
            switch (option)
            {
                case 1:
                    tab = obj.input();
                
                    System.out.println();
                    obj.transposé(tab);
                    System.out.println();
                    System.out.println();
                    break;
                  
                case 2:
                    tab = obj.input();
                
                    System.out.println();
                    obj.transpose2(tab);
                    System.out.println();
                    System.out.println();
                    break;
                    
                case 3:
                    tab = obj.input();
                
                    System.out.println();
                    System.out.println(obj.addDiag(tab));
                    System.out.println();
                    System.out.println();
                    break;
                    
                case 4:
                    tab = obj.input();
                    System.out.println("\nLevel: ");
                    int level = sc.nextInt();
                    
                    System.out.println();
                    double[][] rotated = obj.rotate(tab, level);
                    System.out.println();
                    System.out.println();
                    System.out.println("Level: (Input 0 to exit)");
                    int temp = sc.nextInt();
                    while (temp != 0)
                    {
                        System.out.println();
                        rotated = obj.rotate(rotated, temp);
                        System.out.println();
                        System.out.println();
                        System.out.println("Level: (Input 0 to exit)");
                        temp = sc.nextInt();
                        System.out.println();
                    }
                    break;
                    
                case 5:
                    System.out.println("\nMatrix 1");
                    double [][] tab1 = obj.input();
                    System.out.println("\nMatrix 2");
                    double [][] tab2 = obj.input();
                    
                    System.out.println("\nMultiplication: ");
                    System.out.println();
                    obj.print(tab1);
                    System.out.println();
                    System.out.println();
                    obj.print(tab2);
                    System.out.println();
                    System.out.println();
                    if(tab1[0].length != tab2.length)
                    {
                        System.out.println("Invalid size\n");
                        break;
                    }
                    obj.print(obj.mult(tab1, tab2));
                    System.out.println();
                    System.out.println();
                    break;
            }
            System.out.println("0. Exit\n1. Transpose\n2. Transpose2\n3. Add Diagonals\n4. Rotate\n5. Multiply\n");
            option = sc.nextInt();
        }
    }
}    

