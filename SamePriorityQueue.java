package es.uned.lsi.eped.pract2020_2021;

import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.Queue;
import es.uned.lsi.eped.DataStructures.QueueIF;

/*Representa una cola con un nivel de prioridad asignado determinado*/
public class SamePriorityQueue<E> implements QueueIF<E>,Comparable<SamePriorityQueue<E>>{
 
  //LA DEFINICIÓN DE LOS ATRIBUTOS DE LA CLASE ES TAREA DE CADA ESTUDIANTE
	//Cola que almacena elementos de la misma prioridad
	private Queue<E> Cola;
	//Prioridad de la cola en formato int
	private int Prioridad;

  /* OPERACIONES PROPIAS DE ESTA CLASE */

  /*constructor por defecto: crea cola vacía con la prioridad dada por parámetro.
   *@param p: nivel de prioridad asociado a la cola
  */
  SamePriorityQueue(int p){Cola = new Queue<E>(); Prioridad = p; }

  /* Devuelve la prioridad de la cola
   * @return prioridad de la cola
   */
  public int getPriority(){return Prioridad; }
 
  /* OPERACIONES PROPIAS DE QUEUEIF */

  /*Devuelve el primer elemento de la cola
   * @Pre !isEmpty()
   */
  public E getFirst() {return Cola.getFirst(); }
 
  /*Añade un elemento a la cola de acuerdo al orden de llegada*/
  public void enqueue(E elem) {Cola.enqueue(elem); }

  /*Elimina un elemento a la cola de acuerdo al orden de llegada
   * @Pre !isEmpty()
  */
  public void dequeue() {Cola.dequeue(); }

  /* OPERACIONES PROPIAS DEL INTERFAZ SEQUENCEIF */

  /*Devuelve un iterador para la cola*/
  public IteratorIF<E> iterator() {return Cola.iterator(); }
 
  /* OPERACIONES PROPIAS DEL INTERFAZ COLLECTIONIF */

  /*Devuelve el número de elementos de la cola*/
  public int size() {return Cola.size(); }

  /*Decide si la cola está vacía*/
  public boolean isEmpty() {return Cola.isEmpty();}
 
  /*Decide si la cola contiene el elemento dado por parámetro*/
  public boolean contains(E e) {return Cola.contains(e); }
 
  /*Elimina todos los elementos de la cola*/
  public void clear() {Cola.clear();}
 
  /* OPERACIONES PROPIAS DEL INTERFAZ COMPARABLE */
 
  /*Comparación entre colas según su prioridad
   * Salida:
   *  - Valor >0 si la cola tiene mayor prioridad que la cola dada por parámetro
   *  - Valor 0 si ambas colas tienen la misma prioridad
   *  - Valor <0 si la cola tiene menor prioridad que la cola dada por parámetro
   */
  public int compareTo(SamePriorityQueue<E> o) {return this.getPriority() - o.getPriority();}

}

