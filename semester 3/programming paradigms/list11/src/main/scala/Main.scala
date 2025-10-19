object Main {
  def main(args: Array[String]): Unit = {
    trait Cage[+A] {
      val content: A //covariant trait cage
    }
    trait trainer[-A] {
      def training(input: A): Unit =
      {
        println("the trainer is training something") //contravariant trait trainer
      }
    }
    class animalCage(input: animal) extends Cage[animal]
    {
      override val content: animal = input
    }
    class animalTrainer extends trainer[animal]
    {
      override def training(input: animal): Unit = {
          input match
          {
            case _: cat => println("the trainer is training a cat")
            case _: dog => println("the trainer is training a dog")
          }
      }
    }
    val cat = new cat()
    val dog = new dog()
    val catCage=new animalCage(cat)
    val animalCage: Cage[animal]=catCage //as cage is covariant, it can take in objects of a subclass of its type
    catCage.content.pet()
    animalCage.content.pet()
    val animalTrainer: trainer[dog]=new animalTrainer //as trainer is contravariant it can take subclasses of the superclass of its type
    animalTrainer.training(dog)



    case class card(number: Int, name: String, set: String)
    class itemRepository[T] extends repository[T] with sorting[T] with filtering[T]
    val cardRepository=new itemRepository[card]
    val exCard = card(1, "bidoof", "xy base")
    cardRepository.add(exCard)
    cardRepository.add(card(356, "pikachu", "surging sparks"))
    cardRepository.add(card(456, "ogrepon", "prismatic evolutions"))
    cardRepository.add(card(12, "ogrepon", "twilight masquarade"))
    println(cardRepository.content)
    cardRepository.remove(exCard)
    println(cardRepository.content)
    cardRepository.sort(_.set)
    println(cardRepository.content)
    cardRepository.sort( _.number)
    println(cardRepository.content)
    cardRepository.filter(_.set!="surging sparks")
    println(cardRepository.content)
  }
}