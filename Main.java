import java.util.*;

public class Main {

  public static void main(String[] args) {
    // One FD Set = A --> B, AB --> C
    //FD f1 = new FD(Set.of("A"), Set.of("A", "B")); // A --> B
    //FD f2 = new FD(Set.of("A", "B"), Set.of("C")); // AB --> C
    //FDSet fdset = new FDSet(f1, f2);

    // Another FD set: AB --> B, A --> BC, AB --> C
    //FD g1 = new FD(Set.of("A", "B"), Set.of("B")); // AB --> B
    //FD g2 = new FD(Set.of("A"), Set.of("C", "B")); // A --> BC
    //FD g3 = new FD(Set.of("A", "B"), Set.of("C")); // AB --> C
    //FDSet fdset2 = new FDSet(g1, g2, g3);

    // Output FD closures
    //System.out.println(FDUtil.fdSetClosure(fdset));
    //System.out.println(FDUtil.fdSetClosure(fdset2));

    // Test FD equality: fdset and gdset are logically equivalent iff their closures are equal
    //System.out.println("Equals? " + (fdset.equals(fdset2)));
    //System.out.println("Equivalent? " + (fdset.isEquivalentTo(fdset2)));

    //Set<String> s = new TreeSet<>(Set.of("A", "B", "C")); // s = {A,B,C}
    //System.out.println(FDUtil.powerSet(s)); // P(s) = { {}, {A}, {B}, ... , {A,B,C} }

    // if relation R has just one attribute (X), then give me all the trivial dependencies!
    //System.out.println(FDUtil.trivial(Set.of("X")));

    // if relation R has attributes (A,B), then give me all the trivial dependencies!
    //System.out.println(FDUtil.trivial(Set.of("A","B")));

    //FD f1 = new FD(Set.of("A"), Set.of("B"));       // f1 = A --> B
    //FD f2 = new FD(Set.of("A", "B"), Set.of("C"));  // f2 = A,B --> C
    //FDSet fdset = new FDSet(f1, f2);

    // We want to augment fdset with {C}
    //Set<String> attrs = new HashSet<>(Set.of("C"));
    //System.out.println(FDUtil.augment(fdset, attrs));

    //FD f1 = new FD(Set.of("A"), Set.of("A", "B")); // f1 = A --> A,B
    //FD f2 = new FD(Set.of("A", "B"), Set.of("C")); // f2 = A,B --> C
    //FD f3 = new FD(Set.of("C"), Set.of("D"));      // f3 = C --> D
    //FDSet fdset = new FDSet(f1, f2, f3);
    //System.out.println(FDUtil.transitive(fdset));

    //FD f1 = new FD(Set.of("A"), Set.of("C", "B")); // f1 = A --> C,B
    //FD f2 = new FD(Set.of("C"), Set.of("D", "E")); // f2 = C --> D,E
    //FDSet fdset = new FDSet(f1, f2);
    //System.out.println(FDUtil.transitive(fdset));

    //FD f1 = new FD(Set.of("A"), Set.of("B"));       // f1 = A --> B
    //FD f2 = new FD(Set.of("A", "B"), Set.of("C"));  // f2 = A,B --> C
    //FDSet fdset = new FDSet(f1, f2);
    //System.out.println(FDUtil.fdSetClosure(fdset));

    // One FD Set = A --> A,B and A,B --> C
    FD f1 = new FD(Set.of("A"), Set.of("A", "B")); // A --> A,B
    FD f2 = new FD(Set.of("A", "B"), Set.of("C")); // A,B --> C
    FDSet fdset = new FDSet(f1, f2);

    // Another FD set: A,B --> B and A --> B,C and A,B --> C
    FD g1 = new FD(Set.of("A", "B"), Set.of("B")); // A,B --> B
    FD g2 = new FD(Set.of("A"), Set.of("C", "B")); // A --> B,C
    FD g3 = new FD(Set.of("A", "B"), Set.of("C")); // A,B --> C
    FDSet fdset2 = new FDSet(g1, g2, g3);

    // Test FD set equality: fdset and fdset2 are logically equivalent iff their closures are equivalent
    System.out.println("Logically Equivalent? " + (fdset.isEquivalentTo(fdset2)));

  }
}
