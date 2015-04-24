package scrooge.shapeless.example


import shapeless._

trait Show[T] {
  def show(t: T): String
}

object Show extends LabelledTypeClassCompanion[Show] {
  implicit object ShowDouble extends Show[Double] {
    override def show(t: Double) = t.toString
  }

  implicit object StringDouble extends Show[String] {
    override def show(t: String) = t
  }

  object typeClass extends LabelledTypeClass[Show] {
    override def emptyProduct = new Show[HNil] {
      override def show(t: HNil) = ""
    }
    override def emptyCoproduct = new Show[CNil] {
      override def show(t: CNil) = ""
    }

    override def product[H, T <: HList](name: String, sh: Show[H], st: Show[T]) = new Show[H :: T] {
      override def show(t: H :: T) = {
        val head = sh.show(t.head)
        t match {
          case h :: HNil => s"$name = $head"
          case h :: tail => s"$name = $head, ${st.show(tail)}"
        }
      }
    }

    override def coproduct[L, R <: Coproduct](name: String, sl: => Show[L], sr: => Show[R]) = new Show[L :+: R] {
      override def show(lr: L :+: R) = lr match {
        case Inl(l) => s"$name(${sl.show(l)})"
        case Inr(r) => s"${sr.show(r)}"
      }
    }

    override def project[F, G](instance: => Show[G], to: (F) => G, from: (G) => F) = new Show[F] {
      override def show(f: F) = instance.show(to(f))
    }
  }
}

case class Prod(a: Double, b: String)
