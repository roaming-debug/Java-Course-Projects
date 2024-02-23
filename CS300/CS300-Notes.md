## Array

Using Oversized array needs to pass its size and array reference.

Using perfect sized array only needs to pass its reference.



## Objects

In programming, an **object** is a grouping of data (variables) and operations that can be performed on that data (methods).

Users do not need to **know** or **modify** the *variables* that will be used during inner process.



**Comparing wrapper class** objects using relational operators.

| `objectVar == objectVar`  (also applies to !=)              | DO NOT USE. Compares references to objects, not the value of the objects. |
| ----------------------------------------------------------- | ------------------------------------------------------------ |
| `objectVar == primitiveVar`  (also applies to !=)           | OK. Compares value of object to value of primitive variable. |
| `objectVar == 100`  (also applies to !=)                    | OK. Compares value of object to literal constant.            |
| `objectVar < objectVar`  (also applies to <=, >, and >=)    | OK. Compares values of objects.                              |
| `objectVar < primitiveVar`  (also applies to <=, >, and >=) | OK. Compares values of object to value of primitive.         |
| `objectVar < 100`  (also applies to <=, >, and >=)          | OK. Compares values of object to literal constant.           |



Using `equals()` or `compareTo()` to compare between two wrapper class types.



Instances of wrapper classes, such as Integer and Double, and the String class are defined as **immutable**, meaning that a programmer cannot modify the object's contents after initialization; new objects must be created instead.

### Memory Management

ArrayList will face performance problem when dealing with add/remove operations.



Memory Regions:

**method area**: the code and static memory regions are actually integrated into a region of memory

**The heap** is also called **free store**

**The stack** is also called **automatic memory**, since the memory is automatically allocated and deallocated.

**Static memory**

**Code**



Garbage Collection:

In order to determine which allocated objects the program is currently using at runtime, the Java virtual machine keeps a count, known as a **reference count**, of all reference variables that are currently referring to an object. If the reference count is zero, then the object is considered an **unreachable object** and is eligible for garbage collection, as no variables in the program refer to the object.



## Generics

```java
public static <TheType extends Comparable<TheType>> TheType fooBar(TheType item1) {}
public class TripleItem <TheType extends Comparable<TheType>>{}
```