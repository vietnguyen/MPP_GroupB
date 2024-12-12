### A. ArrayList implements 6 interfaces in Java.
1. Serializable
Allows the ArrayList objects to be serialized (converted into a byte stream) so they can be transferred or saved and later restored.
2. Cloneable
Enables the ArrayList to support cloning, allowing the creation of a shallow copy of the ArrayList instance.
3. Iterable<E>
Allows the ArrayList to be the target of the enhanced for-each loop, providing the iterator() method.
4. Collection<E>
Defines basic operations on a collection, such as adding, removing, and checking the size of the collection.
5. List<E>
Extends Collection to provide ordered collection functionality, such as accessing elements by index and list-specific operations.
6. RandomAccess
Indicates that the ArrayList supports fast (constant-time) random access to elements by index.

ArrayList extends one supper class
`AbstractList<E>`
Provides a skeletal implementation of the List interface to reduce the effort required to implement the List interface.

### E. Diamond Problem 

1. Type D is a class: interface B and C can have default methods with the same signature. Then class D must override the method.
2. Type D is an interface: interface B and C can have default methods with the same signature. Then interface D must define its own default method with same signature.