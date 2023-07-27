/**
 * <p>Title: Node</p>
 *
 * <p>Description: Node object that will hold hockey player's names as the key
 * and a hockey player object as the value.</p>
 *
 * @author Alberto Fernandez (N00858494@students.ncc.edu)
 */
public class Node<K,V> {
	protected K key;
	protected V value;
	Node<K,V> next;
	
	public Node(K key, V value) 
	{
		this.key = key;
		this.value = value;
		next = null;
	}

}
