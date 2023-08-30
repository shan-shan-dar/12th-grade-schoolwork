import java.util.Scanner;

public class SimultLineEq
{
    double[][] eqs;
    double[][] matrix;
    double[][] soln;
    
    //# Initializers
    
    public void init(double[][] eqs)
    {
        this.eqs = eqs;
        
        matrix = new double[eqs.length][eqs[0].length - 1];
        for (int i = 0; i < eqs.length; i++)
        {
            for (int j = 0; j < eqs[0].length - 1; j++)
            {
                matrix[i][j] = eqs[i][j];
            }
        }
        
        soln = new double[eqs.length][1];
        for (int i = 0; i < eqs.length; i++)
        {
            soln[i][0] = eqs[i][eqs[0].length - 1];
        }
    }
    
    //# Row Transformations
    
    public double[][] scalarMult(double[][] mat, int row, double scalar)
    {
        for (int i = 0; i < mat[0].length; i++)
        {
            mat[row][i] = mat[row][i] * scalar;
        }
        
        return mat;
    }
    
    public double[][] addRow(double[][] mat, int row1, int row2, double scalar)
    {
        for (int i = 0; i < mat[0].length; i++)
        {
            mat[row1][i] = mat[row1][i] + (scalar * mat[row2][i]);
        }
        
        return mat;
    }
    
    public double[][] subtractRow(double[][] mat, int row1, int row2, double scalar)
    {
        return addRow(mat, row1, row2, -scalar);
    }
    
    //# Solve
    
    public double[][] solve(double[][] mat, double[][] soln)
    {
        if (determinant(mat) == 0)
        {
            return null;
        }
        
        for (int i = 0; i < mat[0].length; i++)
        {   
            //make i,i element 1
            double temp = 1/mat[i][i];
            mat = scalarMult(mat, i, temp);
            soln = scalarMult(soln, i, temp);
            
            for (int j = 0; j < mat.length; j++)
            {
                if (i != j)
                {
                    //make j,i element 0
                    double temp2 = mat[j][i];
                    mat = subtractRow(mat, j, i, temp2);
                    soln = subtractRow(soln, j, i, temp2);
                }
            }
        }
        
        return soln;
    }
    
    //# Inverse
    
    public double[][] inverse(double[][] mat)
    {
        int n = mat.length;
        double[][] unit = new double[n][n];
        
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (i == j)
                {
                    unit[i][j] = 1;
                }
                else
                {
                    unit[i][j] = 0;
                }
            }
        }   
        
        return solve(mat, unit);
    }
    
    //# Interface
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        
        int option;
        
        System.out.println("0. Exit\n1. Solve Simultaneous Linear Equations\n2. Find Inverse\n");
        option = sc.nextInt();

        while(option != 0)
        {
            SimultLineEq eq = new SimultLineEq();
            System.out.println();
            
            switch(option){
                
                case 1:
                    eq.init(eq.inputEqs());
                    
                    double[][] solution = eq.solve(eq.matrix, eq.soln);
                    
                    if (solution == null)
                    {
                        System.out.println("No Solution");
                        break;
                    }
                    
                    System.out.println("Solution:");
                    eq.printLinear(solution);
                    break;
                
                case 2:
                    double[][] mat = eq.inputMat();
                    
                    System.out.println();
                    double[][] inverse = eq.inverse(mat);
                    
                    if (inverse == null)
                    {
                        System.out.println("No Inverse");
                        break;
                    }
                    
                    System.out.println();
                    System.out.println("Inverse:");
                    eq.print(eq.inverse(mat));
                    break;
                }
                
            System.out.println();
            System.out.println("0. Exit\n1. Solve Simultaneous Linear Equations\n2. Find Inverse\n");
            option = sc.nextInt();
        }
        
        System.out.println();
        System.out.println(":)");
    }
    
    public double[][] inputEqs()
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("No. of variables:");
        int n = sc.nextInt();
        
        double[][] mat = new double[n][n+1];
        
        System.out.println();
        for (int i = 0; i < n; i++)
        {
            System.out.println("Coefficients of equation " + (i + 1) + ":");
            for (int j = 0; j < n+1; j++)
            {
                double coefft = sc.nextDouble();
                mat[i][j] = coefft;
            }
            System.out.println();
        }
        
        return mat;
    }
    
    public double[][] inputMat()
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Size of matrix");
        int n = sc.nextInt();
        
        System.out.println();

        double[][] mat = new double[n][n];

        System.out.println("Enter values of the matrix");

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                int value = sc.nextInt();
                mat[i][j] = value;
            }
        }
        
        return mat;
    }
    
    //# Determinant Calculation
    
    public double determinant(double[][] mat)
    {
        double det = 0;
        
        if (mat.length == 1)
        {
            return mat[0][0];
        }
        
        for (int i = 0; i < mat[0].length; i++)
        {
            double val = Math.pow((-1), (i)) * mat[0][i];
            double cofac = val * determinant(trimMatrix(mat, 0, i));
            
            det += cofac;
        }
        
        return det;
    }
    
    //# Helper Functions
    
    public double[][] trimMatrix(double[][] mat, int row, int col)
    {
        double[][] trimMat = new double[mat.length - 1][mat[0].length - 1];
        
        int r = 0;
        for (int i = 0; i < mat.length; i++)
        {
            int c = 0;
            if(i != row)
            {
                for (int j = 0; j < mat[0].length; j++)
                { 
                    if (j != col)
                    {
                        trimMat[r][c] = mat[i][j];
                        c++;
                    }
                }
                r++;
            }
        }
        
        return trimMat;
    }
    
    public void print(double[][] tab)
    {
        for (int row=0; row < tab.length; row += 1)
        {   
            for (int col = 0; col < tab[row].length ; col += 1)
            {
                if (tab[row][col] >= 0)
                {
                    System.out.print(" ");
                }
                System.out.format("%.2f", tab[row][col]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }
    
    public void printLinear(double[][] tab)
    {
        for (int i=0; i < tab.length; i ++)
        {
            if (tab[i][0] >= 0)
                {
                    System.out.print(" ");
                }
            System.out.println(tab[i][0]);
        }
    }
}
