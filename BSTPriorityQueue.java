package es.uned.lsi.eped.pract2020_2021;

import es.uned.lsi.eped.DataStructures.BSTree;
import es.uned.lsi.eped.DataStructures.Collection;
import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.BSTreeIF.IteratorModes;


/*Representa una cola con prioridad implementada mediante un árbol binario de búsqueda de SamePriorityQueue*/
public class BSTPriorityQueue<E> extends Collection<E> implements PriorityQueueIF<E> {
 
  //LA DEFINICIÓN DE LOS ATRIBUTOS DE LA CLASE ES TAREA DE CADA ESTUDIANTE
	//Arbol de búsqueda binaria que contiene elementos SamePriorityQueue
	private BSTree<SamePriorityQueue<E>> Arbol;
	//Tamaño del árbol de búsqueda binaria que contiene elementos SamePriorityQueue
	private int Size;

  /* Clase privada que implementa un iterador para la *
   * cola con prioridad basada en secuencia.          */
  public class PriorityQueueIterator implements IteratorIF<E> {

    //LA DEFINICIÓN DE LOS ATRIBUTOS DE LA CLASE ES TAREA DE CADA ESTUDIANTE
	  //Iterador que recorre arbol de colas de prioridad
	  private IteratorIF<SamePriorityQueue<E>> ArbolIt;
	  ////Iterador que recorre cola de elementos de la misma prioridad
	  private IteratorIF<E> ColaIt;

    /*Constructor por defecto*/
    protected PriorityQueueIterator()
    {
    	ArbolIt = Arbol.iterator(IteratorModes.REVERSEORDER);
    	if (ArbolIt.hasNext()){ColaIt = ArbolIt.getNext().iterator();}
    }

    /*Devuelve el siguiente elemento de la iteración*/
    public E getNext() 
    {
    	if (ColaIt.hasNext()) {return ColaIt.getNext();}
    	else
    	{
    		ColaIt = ArbolIt.getNext().iterator();
    		return ColaIt.getNext();
    	}
    	
    }
    
    /*Comprueba si queda algún elemento por iterar*/
    public boolean hasNext() 
    {
    	if (Arbol.isEmpty()) {return false;}
    	else
    	{
    		if (ArbolIt.hasNext()) {return true;}
        	else 
        	{
        		if (ColaIt.hasNext()) {return true;}
        	}
    	}
    	return false;
    }
 
    /*Reinicia el iterador a la posición inicial*/
    public void reset() 
    {	
    	ArbolIt.reset();
    	if (ArbolIt.hasNext()) {ColaIt.reset();}
    }
  }



  /* OPERACIONES PROPIAS DE ESTA CLASE */

  /*constructor por defecto: crea cola con prioridad vacía
   */
  BSTPriorityQueue(){ Arbol = new BSTree<>(); Size = 0; }

  /* OPERACIONES PROPIAS DE LA INTERFAZ PRIORITYQUEUEIF */

  /*Devuelve el elemento más prioritario de la cola y que
   *llegó en primer lugar
   * @Pre !isEmpty()
   */
  public E getFirst() 
  {
	  return this.iterator().getNext();
  }
 
  /*Añade un elemento a la cola de acuerdo a su prioridad
   *y su orden de llegada
   */
  public void enqueue(E elem, int prior) 
  {
	  SamePriorityQueue<E> SPQActual;
	  IteratorIF<SamePriorityQueue<E>> BSTreeIt;
	  BSTreeIt = Arbol.iterator(IteratorModes.REVERSEORDER);
	  if (Arbol.isEmpty()) 
	  {
		  SPQActual = new SamePriorityQueue<E>(prior);
		  SPQActual.enqueue(elem);
		  Arbol.add(SPQActual);
		  Size++;
	  }
	  else
	  {
		  while (BSTreeIt.hasNext())
		  {
			  SPQActual = BSTreeIt.getNext();
			  if (SPQActual.getPriority()==prior)
			  {
				  Arbol.remove(SPQActual);
				  SPQActual.enqueue(elem);
				  Arbol.add(SPQActual);
				  Size++;
				  break;
			  }
			  else if (SPQActual.getPriority()<prior||(SPQActual.getPriority()>prior&&!BSTreeIt.hasNext()))
			  {
				  SPQActual = new SamePriorityQueue<E>(prior);
				  SPQActual.enqueue(elem);
				  Arbol.add(SPQActual);
				  Size++;
				  break;
			  }
		  }
	  }
  }

  /*Elimina el elemento más prioritario y que llegó a la cola
   *en primer lugar
   * @Pre !isEmpty()
   */
  public void dequeue() 
  {
	 IteratorIF<SamePriorityQueue<E>> BSTreeIt;
	 BSTreeIt = Arbol.iterator(IteratorModes.REVERSEORDER);
	 BSTreeIt.getNext().dequeue();
	 Size--;
	 BSTreeIt.reset();
	 if (BSTreeIt.getNext().isEmpty()) 
	 {
		 BSTreeIt.reset();
		 Arbol.remove(BSTreeIt.getNext());
	 }
  }

  /* OPERACIONES PROPIAS DE LA INTERFAZ SEQUENCEIF */

  /*Devuelve un iterador para la cola*/
  public IteratorIF<E> iterator() {return new PriorityQueueIterator(); }
 
  /* OPERACIONES PROPIAS DE LA INTERFAZ COLLECTIONIF */

  /*Devuelve el número de elementos de la cola*/
  public int size() 
  {
	  return Size;
  }

  /*Decide si la cola está vacía*/
  public boolean isEmpty() {return Arbol.isEmpty();}
 
  /*Decide si la cola contiene el elemento dado por parámetro*/
  public boolean contains(E e) 
  {
	  IteratorIF<E> It = this.iterator();
	  while (It.hasNext())
	  {
		  if (It.getNext().equals(e))
		  {
			  return true;
		  }
	  }
	  return false;
  }
 
  /*Elimina todos los elementos de la cola*/
  public void clear() {Arbol.clear(); Size = 0; }
 
}

