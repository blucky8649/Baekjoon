
// Java implementation of the approach
import java.util.*;
 
class MainGFG
{
 
static int ans;
 
// DFS function to traverse the tree and find
// number of special nodes
static void dfs(int val[], int n, Vector<Integer> adj[], int v,
        HashSet<Integer> values)
{
 
    // If value of current node is already
    // present earlier in path then this
    // node and all other nodes connected to
    // it are not special
    if (values.contains(val[v]))
        return;
 
    // Insert value of current node in
    // set of values traversed
    ans++;
    values.add(val[v]);
 
    // Call dfs on all adjacent nodes
    for (int ele : adj[v])
    {
        dfs(val, n, adj, ele, values);
    }
 
    // Erase value of current node as all paths
    // passing through current node are traversed
    values.remove(val[v]);
}
 
// Driver code
public static void main(String[] args)
{
    int val[] = { 0, 2, 1, 4, 3, 4, 8, 10, 2, 5, 1 };
    int n = val.length;
 
    Vector<Integer> []adj = new Vector[n];
    for(int i = 0; i < n ; i++)
    {
        adj[i] = new Vector<Integer>();
    }
    adj[1].add(2);
    adj[2].add(3);
    adj[3].add(4);
    adj[3].add(5);
    adj[1].add(6);
    adj[1].add(7);

 
    ans = 0;
    dfs(val, n, adj, 1, new HashSet<Integer>());
    System.out.print(ans);
}
}
 
// This code is contributed by Rajput-Ji