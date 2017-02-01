package ku.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An ilterator to ilterate through the array without knowing
 * the structure of the array.
 * 
 * @author Sirasath Piyapootinun
 *
 * @param <T>
 * 	is the type of element returned by this iterator.
 */	

public class ArrayIterator<T> implements Iterator<T> {
	
	/** attribute for the array we want to iterate over. */
	private T[ ] array;
	/** attribute for the index of the element. */
	private int cursor;
	/** attribute for calling remove(). If true, remove() can be called. */
	private boolean check;
	
	/**
	* Initialize a new array iterator with the array to process.
	* @param array 
	* 	is the array to iterate over
	*/
	public ArrayIterator(T[] array) {
		this.array = array;
		this.check = false;
	}
	
	/**
	 * Return true if the iteration has more non-null elements.
	 * 
	 * @return true if the iteration has more non-null elements,
	 *         and false if there are no more non-null elements.
	 */
	@Override
	public boolean hasNext() {
		for(int i = this.cursor; i < this.array.length; i++) {
			if(this.array[i] != null) {
				this.cursor = i;
				return true;
			}
		}
		return false;
	}
	
	/** 
	* Return the next non-null element from array, if any.
	* @return the next non-null element in the array.
	* @throws NoSuchElementException 
	* 		if there are no more elements to return.
	*/
	@Override
	public T next() {
		if(hasNext()) {
			this.check = true;
			this.cursor++;
			return array[this.cursor - 1];
		} 
		throw new NoSuchElementException();
	}
	
	/**
	 * Returns the most recent element returned by next() 
	 * from the array. It will set the value to null. 
	 * This method can only be called after next(). 
	 * 
	 * @throws IllegalStateException()
	 * 		 if remove() is called without calling 
	 * 		 next() first or if remove() is called
	 * 		 more than once.
	 */
	public void remove() {
		if(this.check) {
			array[this.cursor - 1] = null;
			this.check = false;
		} else
			throw new IllegalStateException();
	}
}
