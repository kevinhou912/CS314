


public boolean add(E val){
	int oldSize = size;
	root = addHelp(root, val);
	return oldSize != size;
}

private BSTNode<E> addHelp(BSTNode<E>, E val){
	if(n == null){
		size++;
		return new BESTNode<>(val);
	}else{
		int dir = val.compareTo(n.data);
		if(dir < 0){
		n.left = add(n.left, val);
		}else if(dir > 0){
		n.right = add(n.right, val);
		}
		return n;									
	}
}

public int height(){
	return htHelp(root);
}

private int htHelp(BSTNode<E> ){
	if(n == null){
	return -1;
	}else{
	return 1 + Math.max(htHelp(n.left), htHelp(n.right));
	}

public boolean remove(E val){
	int oldSize = size;
	root = removeHelp(root, val);
	return oldSize != size;
}

private BSTNode<E> removeHelp(BSTNode<E> n , E val){
	if(n == null){
		return n;
	}else{
		int dir = val.compareTo(n.data);
		if(dir < 0){
			n.left = removeHelp(n.left,val);
		}else if(dir > 0){
			n.right= removeHelp(n.right,val);
		}else{
			size--;
			if(n.left == null && n.right == null){
				return null;
			}else if(n.right == null){
				return n.left;
			}else if(n.left == null){
				return n.right
			}else{
				n.data = getMax(n.left);
				n.left = removeHelp(n.left, n.data);
				size++;
			}
		}
	}
}

