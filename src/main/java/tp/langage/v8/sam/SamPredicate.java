package tp.langage.v8.sam;

/* SAM= Single Abstract Method :
 * interface avec une seule méthode 
 * (marquée avec @FunctionalInterface de java >= 8)
 */

/*L'annotation @FunctionalInterface (du jdk >= 1.8) est facultative
  elle permet seulement au compilateur de vérifier que l'interface comporte une seule méthode (ordinaire) */
@FunctionalInterface
//un équivalent prédéfini correspond à java.util.function.Predicate (depuis java 8)
public interface SamPredicate<E> {
    boolean test(E e); //retourne true si l'entité e (de type E) satisfait certains critères
    default String getAuthor() { return "developpeur fou"; } //méthode par défaut (alias "extension method" alias "defender method" )
    //les méthodes par défaut ont permis d'ajouter de nouvelles fonctionalités à java8 sans remettre en question les interface java7
    public static void methodeStatiqueAutoriseeSurInterfaceDepuisJava8(String msg){ System.out.println(msg); }
    //les méthodes statiques au sein des interfaces permettent d'éviter des mini classes utilitaires péripheriques
}
