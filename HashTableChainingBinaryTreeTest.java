import java.util.Scanner;
 
/* Node for Binary Tree */
class BTNode
{
    BTNode left, right;
    int data;
 
    /* Constructor */
    public BTNode(int x)
    {
        data = x;
        left = null;
        right = null;
    }
}
 
/* Class HashTableChainingBinaryTree */
class HashTableChainingBinaryTree
{
    private BTNode[] table;
    private int size ;
 
    /* Constructor */
    public HashTableChainingBinaryTree(int tableSize)
    {
        table = new BTNode[ nextPrime(tableSize) ];
        size = 0;
    }
    /* Function to check if hash table is empty */
    public boolean isEmpty()
    {
        return size == 0;
    }
    /* Function to clear hash table */
    public void makeEmpty()
    {
        int l = table.length;
        table = new BTNode[l];
        size = 0;
    }
    /* Function to get size */
    public int getSize()
    {
        return size;
    }
    /* Function to insert an element */
    public void insert(int val)
    {
        size++;
        int pos = myhash(val);        
        BTNode root = table[pos];
        root = insert(root, val);
        table[pos] = root;        
    }
    /* Function to insert data */
    private BTNode insert(BTNode node, int data)
    {
        if (node == null)
            node = new BTNode(data);
        else
        {
            if (data <= node.data)
                node.left = insert(node.left, data);
            else
                node.right = insert(node.right, data);
        }
        return node;
    }
    /* Function to remove an element */
    public void remove(int val)
    {
        int pos = myhash(val);        
        BTNode root = table[pos];
        try
        {
            root = delete(root, val);    
            size--;
        }
        catch (Exception e)
        {
            System.out.println("\nElement not present\n");        
        }        
        table[pos] = root;        
    }
    /* Function to remove an element */
    private BTNode delete(BTNode root, int k)
    {
        BTNode p, p2, n;
        if (root.data == k)
        {
               BTNode lt, rt;
            lt = root.left;
            rt = root.right;
            if (lt == null && rt == null)
                return null;
            else if (lt == null)
            {
                p = rt;
                return p;
            }
            else if (rt == null)
            {
                p = lt;
                return p;
            }
            else
            {
                p2 = rt;
                p = rt;
                while (p.left != null)
                    p = p.left;
                p.left = lt;
                return p2;
            }
        }
        if (k < root.data)
        {
            n = delete(root.left, k);
            root.left = n;
        }
        else
        {
            n = delete(root.right, k);
            root.right = n;             
        }
        return root;
    }
    /* Function myhash */
    private int myhash(Integer x )
    {
        int hashVal = x.hashCode( );
        hashVal %= table.length;
        if (hashVal < 0)
            hashVal += table.length;
        return hashVal;
    }
    /* Function to generate next prime number >= n */
    private static int nextPrime( int n )
    {
        if (n % 2 == 0)
            n++;
        for ( ; !isPrime( n ); n += 2);
 
        return n;
    }
    /* Function to check if given number is prime */
    private static boolean isPrime( int n )
    {
        if (n == 2 || n == 3)
            return true;
        if (n == 1 || n % 2 == 0)
            return false;
        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;
        return true;
    }
    /* printing hash table */
    public void printHashTable ()
    {
        System.out.println();
        for (int i = 0; i < table.length; i++)
        {
            System.out.print ("Bucket " + i + ":  ");            
            inorder(table[i]);
            System.out.println();
        }
    }  
    /* inorder traversal */
    private void inorder(BTNode r)
    {
        if (r != null)
        {
            inorder(r.left);
            System.out.print(r.data +" ");
            inorder(r.right);
        }
    }     
}
 
/* Class HashTableChainingBinaryTreeTest */
public class HashTableChainingBinaryTreeTest
{ 
    public static void main(String[] args) 
    {
      
      try
      {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hash Table Test\n\n");
        System.out.println("Enter size");
        /* Make object of HashTableChainingBinaryTree  */
        HashTableChainingBinaryTree htcbt = new HashTableChainingBinaryTree(scan.nextInt() );
     
 
        char ch;
        /*  Perform HashTableChainingBinaryTree operations  */
        do     
        {
            System.out.println("\nHash Table Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. remove"); 
            System.out.println("3. clear");
            System.out.println("4. size"); 
            System.out.println("5. check empty");
 
            int choice = scan.nextInt();            
            switch (choice)  
            {  
            case 1 : 
                System.out.println("Enter integer element to insert");
                htcbt.insert( scan.nextInt() ); 
                break;                          
            case 2 :                 
                System.out.println("Enter integer element to delete");
                htcbt.remove( scan.nextInt() ); 
                break;                        
            case 3 : 
                htcbt.makeEmpty();
                System.out.println("Hash Table Cleared\n");
                break;
            case 4 : 
                System.out.println("Size = "+ htcbt.getSize() );
                break; 
            case 5 : 
                System.out.println("Empty status = "+ htcbt.isEmpty() );
                break;        
            default : 
                System.out.println("Wrong Entry \n ");
                break;    
            }
            /* Display hash table */ 
            htcbt.printHashTable();    
 
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');   
    }
    
  catch(Exception e)
  {
  }
    }

       
}
