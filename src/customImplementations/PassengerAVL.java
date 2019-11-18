/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customImplementations;

/**
 *
 * @author danie
 */
import data.Passenger;

/**
 *
 * @author danie
 */
public class PassengerAVL {
        
       	public Node root; 

	// A utility function to get the height of the tree 
	int height(Node N) { 
		if (N == null) 
			return 0; 

		return N.height; 
	} 

	// A utility function to get maximum of two integers 
	int max(int a, int b) {
        return (a > b) ? a : b;
    }

        private Node Next(Node current) { //inorder sucesor
            if (current.left == null) {
                return current;
            } else {
                return Next(current.left);
            }
        }


	// A utility function to right rotate subtree rooted with y 
	// See the diagram given above. 
	Node rightRotate(Node y) { 
		Node x = y.left; 
		Node T2 = x.right; 

		// Perform rotation 
		x.right = y; 
		y.left = T2; 

		// Update heights 
		y.height = max(height(y.left), height(y.right)) + 1; 
		x.height = max(height(x.left), height(x.right)) + 1; 

		// Return new root 
		return x; 
	} 

	// A utility function to left rotate subtree rooted with x 
	// See the diagram given above. 
	Node leftRotate(Node x) { 
		Node y = x.right; 
		Node T2 = y.left; 

		// Perform rotation 
		y.left = x; 
		x.right = T2; 

		// Update heights 
		x.height = max(height(x.left), height(x.right)) + 1; 
		y.height = max(height(y.left), height(y.right)) + 1; 

		// Return new root 
		return y; 
	} 

	// Get Balance factor of node N 
	int getBalance(Node N) { 
		if (N == null) 
			return 0; 

		return height(N.left) - height(N.right); 
	} 

    public Node insert(Node node, Passenger key) {

        /* 1. Perform the normal BST insertion */
        if (node == null) {
            return (new Node(key));
        }

        if (key.getId() < node.key.getId()) {
            node.left = insert(node.left, key);
        } else if (key.getId() > node.key.getId()) {
            node.right = insert(node.right, key);
        } else // Duplicate keys not allowed 
        {
            return node;
        }

        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left),
                height(node.right));

        /* 3. Get the balance factor of this ancestor 
			node to check whether this node became 
			unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there 
        // are 4 cases Left Left Case 
        if (balance > 1 && key.getId() < node.left.key.getId()) {
            return rightRotate(node);
        }

        // Right Right Case 
        if (balance < -1 && key.getId() > node.right.key.getId()) {
            return leftRotate(node);
        }

        // Left Right Case 
        if (balance > 1 && key.getId() > node.left.key.getId()) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case 
        if (balance < -1 && key.getId() < node.right.key.getId()) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    public Node deleteNode(Node root, Passenger key) {
        if (root == null) {
            return root; //arbol vacio
        }

        if (key.getId() < root.key.getId()) { //buscando a la izquierda
            root.left = deleteNode(root.left, key);
        } else if (key.getId() > root.key.getId()) {
            root.right = deleteNode(root.right, key); //buscando a la derecha
        } else// Lo encontramos
        if ((root.left == null) || (root.right == null)) { //El nodo en cuestion tiene un solo hijo o no tiene (caso facil)
            Node temp = null;

            if (temp == root.left) { //sin hijo izquierdo
                temp = root.right;
            } else { //sin hijo derecho
                temp = root.left;
            }

            if (temp == null) { //no tiene ni hijo derecho ni izquierdo
                temp = root; // guarde la informacion del nodo
                root = null; //borre el nodo
            } else { //solo un hijo
                root = temp; //copie el contenido de los sucesores del nodo
            }
        } else {
            Node temp = Next(root.right); //encuentre el sucesor

            root.key = temp.key; //copie los datos

            root.right = deleteNode(root.right, temp.key); //borre el nodo sucesor, este es temp

        }
        
        if(root==null){
            return root; //si el arbol solo tenia un nodo abandone la recursion      
        }
        
        root.height=max(height(root.left),height(root.right))+1; //actualice la altura
        
        int balance=getBalance(root); //calcular balance de este nodo
        
        if(balance>1&&getBalance(root.left)>=0){
            return rightRotate(root);
        }

        if(balance>1&&getBalance(root.left)<0){
            root.left=rightRotate(root);
            return rightRotate(root);
        }
        
        if(balance<-1&&getBalance(root.right)<=0){
            return leftRotate(root);
        }
        
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        
        return root;
            
        }
        
        
        

	// A utility function to print preorder traversal 
	// of the tree. 
	// The function also prints height of every node 
	public  void preOrder(Node node) { 
		if (node != null) { 
			System.out.println(node.key + " "); 
			preOrder(node.left); 
			preOrder(node.right); 
		} 
	} 
        
    public Node Find(int id) {
        return find(root, id);
    }
        
    private Node find(Node current, int id) {
        if (current.key.getId() == id) {
            return current;
        } else if (current.key.getId() > id) {
            if (current.left != null) {
                return find(current.left, id);
            }
            return current;
        } else {
            if (current.right != null) {
                return find(current.right, id);
            }
            return current;
        }
    }
    
    
  

}