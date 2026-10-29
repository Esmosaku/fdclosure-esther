import java.util.HashSet;
import java.util.Set;

/**
 * This utility class is not meant to be instantitated, and just provides some
 * useful methods on FD sets.
 * 
 * @author Esther Mosaku
 * @version 10/29/2026
 */
public final class FDUtil {
  /**
   * Generates all trivial FDs over the given attribute set.
   * FDs with empty right-hand sides are omitted.
   *
   * @param attrs the relation's attributes; must not be modified
   * @return a new set containing all trivial FDs over the given attributes
   */
  public static FDSet trivial(final Set<String> attrs) {
    FDSet result = new FDSet();

     Set<Set<String>> fullPowerSet1 = powerSet(attrs);
     Set<Set<String>> fullPowerSet2 = powerSet(attrs);

     for(Set<String> eachSet: fullPowerSet1){
      if (!eachSet.isEmpty()){
        for(Set<String> subSet: fullPowerSet2){
          if(!subSet.isEmpty()){
            if(eachSet.containsAll(subSet)){
              result.add(new FD(eachSet, subSet));
            }
          }
        }
      }
     }

    return result;
  }

  /**
   * Augments every FD in the given set of FDs with the given attributes
   * 
   * @param fdset FD Set (Immutable)
   * @param attrs a set of attributes with which to augment FDs (Immutable)
   * @return a set of augmented FDs
   */
  public static FDSet augment(final FDSet fdset, final Set<String> attrs) {
    FDSet result = new FDSet();
    for(FD eachFd: fdset){
      eachFd.addToLeft(attrs);
      eachFd.addToRight(attrs);
      result.add(new FD(eachFd));
    }
    return result;
  }

  /**
   * Exhaustively resolves transitive FDs with respect to the given set of FDs
   * (NB: not the best implementation with a double for loop within a while loop but it passes the test cases)
   * @param fdset (Immutable) FD Set
   * @return all transitive FDs with respect to the input FD set
   */
  public static FDSet transitive(final FDSet fdset) {
    FDSet result = new FDSet();
    FDSet tempFD = new FDSet(fdset);

    while(true){
      int prevLength = result.size();
      for(FD eachFd: tempFD){
        for (FD newTmp: tempFD){
          if (eachFd.equals(newTmp)){
          continue;
          }
          if (eachFd.rightContains(newTmp.getLeft())){
            result.add(new FD(eachFd.getLeft(), newTmp.getRight()));
            tempFD.add(new FD(eachFd.getLeft(), newTmp.getRight()));
          }
        }
      }
      if (result.size() == prevLength){
        break;
      }
    }

    return result;
  }

  /**
   * Generates the closure of the input FD set, omitting FDs with empty
   * right-hand sides. The relation's attributes are assumed to be exactly
   * those appearing on either side of the input FDs.
   *
   * @param fdset input FD set; must not be modified
   * @return a new FD set containing the closure
   */
  public static FDSet fdSetClosure(final FDSet fdset) {
    Set<String> attr = new HashSet<>();
    for (FD eachFd: fdset){
      attr.addAll(eachFd.getLeft());
      attr.addAll(eachFd.getRight());
    }

    FDSet current = new FDSet(fdset); // make a copy of F

    FDSet trivTemp = trivial(attr);
    current.addAll(trivTemp);

    Set<Set<String>> attrSubSet = powerSet(attr);

    while(true){
      int prevLength = current.size();
      for(Set<String> Z: attrSubSet){
        FDSet temp = augment(current, Z);
        current.addAll(temp);
      }
      FDSet newTemp = transitive(current);
      current.addAll(newTemp);
      
      if (current.size() == prevLength){
        break;
      }
    }
    
    return current;
  }

  /**
   * Generates all subsets of the input set, including the empty subset.
   *
   * @param set input set; must not be modified
   * @return the power set of the input set
   */
  public static <E> Set<Set<E>> powerSet(final Set<E> set) {
    Set<Set<E>> finalSet = new HashSet<>();

    if (set.isEmpty()){
      finalSet.add(new HashSet<>());
      return finalSet;
    }
    Set<E> remaining = new HashSet<>(set);
    E element = remaining.iterator().next();
    remaining.remove(element);

    Set<Set<E>> recurseSet = powerSet(remaining);

    for (Set<E> eachSet: recurseSet){
      finalSet.add(eachSet);

      //create new subset that has the union of the current element and the elements in the recurseSet
      Set<E> unionSet = new HashSet<>(eachSet);
      unionSet.add(element);
      finalSet.add(unionSet);
    }

    return finalSet;
  }
}