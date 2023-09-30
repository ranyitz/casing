import casing.Casing

object Example extends App {
  println(Casing.camelCase("foo_bar")) // fooBar
  println(Casing.pascalCase("foo bar")) // FooBar
  println(Casing.snakeCase("fooBar")) // foo_bar
  println(Casing.kebabCase("foo-bar")) // foo-bar
  println(Casing.constantCase("foo.bar")) // FOO_BAR
  println(
    Casing
      .split("fooBarBaz")
      .map(_.toLowerCase())
      .mkString(".")
  ) // foo.bar.baz
}
