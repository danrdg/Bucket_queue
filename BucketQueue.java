package es.uned.lsi.eped.pract2020_2021;

import es.uned.lsi.eped.DataStructures.Collection;
import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.List;

/*Representa una cola con prioridad implementada mediante una secuencia de SamePriorityQueue*/
public class BucketQueue<E> extends Collection<E> implements PriorityQueueIF<E> {
 
  //LA DEFINICIÓN DE LOS ATRIBUTOS DE LA CLASE ES TAREA DE CADA ESTUDIANTE
	//Lista de elementos ordenados por prioridad
	private List<SamePriorityQueue<E>> Lista;
	//Tamaño de la cola con prioridad implementada mediante secuencia de SamePriorityQueue
	private int Size;

  /* Clase privada que implementa un iterador para la *
   * cola con prioridad basada en secuencia.          */
  public class PriorityQueueIterator implements IteratorIF<E> 
  {

    //LA DEFINICIÓN DE LOS ATRIBUTOS DE LA CLASE ES TAREA DE CADA ESTUDIANTE
	  //Iterador que recorre lista de colas de prioridad
	  private IteratorIF<SamePriorityQueue<E>> ListaIt;
	  //Cola de elementos actual
	  private IteratorIF<E> ColaIt;
	 

    /*Constructor por defecto*/
    protected PriorityQueueIterator()
    {
    	ListaIt = Lista.iterator();
    	if (ListaIt.hasNext()) {ColaIt = ListaIt.getNext().iterator();}
    }

    /*Devuelve el siguiente elemento de la iteración*/
    public E getNext() 
    {
    	if (ColaIt.hasNext()) {return ColaIt.getNext();}
    	else
    	{
    		ColaIt = ListaIt.getNext().iterator();
    		return ColaIt.getNext();
    	}
    }
 
    
    /*Comprueba si queda algún elemento por iterar*/
    public boolean hasNext() 
    {
    	if (Lista.isEmpty()) {return false;}
    	else
    	{	
    		if (ListaIt.hasNext()) {return true;}
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
    	ListaIt.reset();
    	if (ListaIt.hasNext()){ColaIt.reset();}
    }

  }


  /* OPERACIONES PROPIAS DE ESTA CLASE */

  /*constructor por defecto: crea cola con prioridad vacía
   */
  BucketQueue(){Lista = new List<>();Size = 0;}

  /* OPERACIONES PROPIAS DE LA INTERFAZ PRIORITYQUEUEIF */

  /*Devuelve el elemento más prioritario de la cola y que
   *llegó en primer lugar
   * @Pre !isEmpty()
   */
  public E getFirst() 
  {
	  return Lista.get(1).getFirst();
  }
 
  /*Añade un elemento a la cola de acuerdo a su prioridad
   *y su orden de llegada
   */
  public void enqueue(E elem, int prior) 
  {
	  IteratorIF<SamePriorityQueue<E>> ListaIt = Lista.iterator();
	  SamePriorityQueue<E> SPQActual;
	  int Cont = 0;
	  if (Lista.isEmpty()) 
	  {
		  Lista.insert(1, new SamePriorityQueue<E>(prior));
		  Lista.get(1).enqueue(elem);
		  Size++;
	  }
	  else
	  {
		  while (ListaIt.hasNext())
		  {
			  SPQActual = ListaIt.getNext();
			  Cont++;
			  if (SPQActual.getPriority()==prior) 
			  {
				  SPQActual.enqueue(elem);
				  Size++;
				  break;
			  }
			  if (SPQActual.getPriority()<prior)
			  {
				  Lista.insert(Cont,new SamePriorityQueue<E>(prior));
				  Lista.get(Cont).enqueue(elem);
				  Size++;
				  break;
			  }
			  if (!ListaIt.hasNext()&&SPQActual.getPriority()>prior)
			  {
				  Lista.insert(Cont+1, new SamePriorityQueue<E>(prior));
				  Lista.get(Cont+1).enqueue(elem);
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
	  Lista.get(1).dequeue();
	  if (Lista.get(1).isEmpty())
	  {Lista.remove(1);}
	  Size--;
  }

  /* OPERACIONES PROPIAS DE LA INTERFAZ SEQUENCEIF */

  /*Devuelve un iterador para la cola*/
  public IteratorIF<E> iterator() {return new PriorityQueueIterator(); }
 
  /* OPERACIONES PROPIAS DE LA INTERFAZ COLLECTIONIF */

  /*Devuelve el número de elementos de la cola*/
  public int size() 
  {
	  return this.Size;
  }

  /*Decide si la cola está vacía*/
  public boolean isEmpty() {return Lista.size() == 0; }
 
  /*Decide si la cola contiene el elemento dado por parámetro*/
  public boolean contains(E e) 
  {
	  IteratorIF<E> It = this.iterator();
	  while (It.hasNext())
	  {
		  if (It.getNext().equals(e)) 
		  {return true;}
	  }
	  return false;
  }
 
  /*Elimina todos los elementos de la cola*/
  public void clear() {Lista.clear(); Size = 0; }
 
}

