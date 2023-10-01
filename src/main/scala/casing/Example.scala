import casing._

private object Example extends App {
  println(camelCase("foo_bar")) // fooBar
  println(pascalCase("foo bar")) // FooBar
  println(snakeCase("fooBar")) // foo_bar
  println(kebabCase("foo-bar")) // foo-bar
  println(constantCase("foo.bar")) // FOO_BAR
  println(
    caseSplit("fooBarBaz")
      .map(_.toLowerCase())
      .mkString(".")
  ) // foo.bar.baz
}
