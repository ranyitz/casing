import minitest._
import casing.test.TestUtils.expect
import casing._

object CasingTest extends SimpleTestSuite {
  test("split") {
    expect(Casing.split("fooBar"), Seq("foo", "Bar"))
    expect(Casing.split("FooBar"), Seq("Foo", "Bar"))
    expect(Casing.split("FOOBar"), Seq("FOO", "Bar"))
    expect(Casing.split("foo_bar"), Seq("foo", "bar"))
    expect(Casing.split("foo-bar"), Seq("foo", "bar"))
    expect(Casing.split("foo.bar"), Seq("foo", "bar"))
    expect(Casing.split("Foo Bar"), Seq("Foo", "Bar"))
    expect(Casing.split("foo bar"), Seq("foo", "bar"))
    expect(Casing.split("FOO_BAR"), Seq("FOO", "BAR"))
    expect(Casing.split("foo/bar"), Seq("foo", "bar"))
    expect(Casing.split(" foo bar "), Seq("foo", "bar"))
    expect(Casing.split("_foo-Bar.Baz_Qux "), Seq("foo", "Bar", "Baz", "Qux"))
  }

  test("split with numbers=false") {
    expect(Casing.split("fooBar123", SplitOptions(numbers = false)), Seq("foo", "Bar123"))
    expect(Casing.split("123foo bar", SplitOptions(numbers = false)), Seq("123foo", "bar"))
    expect(Casing.split("123.foo.bar", SplitOptions(numbers = false)), Seq("123", "foo", "bar"))
    expect(
      Casing.split("Scala2.13.11", SplitOptions(numbers = false)),
      Seq("Scala2", "13", "11")
    )
    expect(Casing.split("1V", SplitOptions(numbers = false)), Seq("1V"))
  }

  test("split with numbers=true (default)") {
    expect(Casing.split("fooBar123"), Seq("foo", "Bar", "123"))
    expect(Casing.split("123foo bar"), Seq("123", "foo", "bar"))
    expect(
      Casing.split("Scala2.13.11"),
      Seq("Scala", "2", "13", "11")
    )
    expect(Casing.split("1V"), Seq("1", "V"))
  }

  test("camelCase") {
    assertEquals(Casing.camelCase("foo bar"), "fooBar")
    assertEquals(Casing.camelCase("FOO BAR"), "fooBar")
    assertEquals(Casing.camelCase("foo bar 123"), "fooBar123")
  }

  test("isCamelCase") {
    assert(Casing.isCamelCase("fooBar"))
    assert(Casing.isCamelCase("FooBar") == false)
    assert(Casing.isCamelCase("foo_bar") == false)
  }

  test("PascalCase") {
    assertEquals(Casing.pascalCase("foo bar"), "FooBar")
    assertEquals(Casing.pascalCase("FOO BAR"), "FooBar")
  }

  test("isPascalCase") {
    assert(Casing.isPascalCase("FooBar"))
    assert(Casing.isPascalCase("fooBar") == false)
    assert(Casing.isPascalCase("foo_bar") == false)
  }

  test("snake_case") {
    assertEquals(Casing.snakeCase("foo bar"), "foo_bar")
    assertEquals(Casing.snakeCase("FooBar"), "foo_bar")
    assertEquals(Casing.snakeCase("FooBar123"), "foo_bar_123")
    assertEquals(
      Casing.snakeCase("FooBar123", options = SplitOptions(numbers = false)),
      "foo_bar123"
    )
  }

  test("isSnakeCase") {
    assert(Casing.isSnakeCase("foo_bar"))
    assert(Casing.isSnakeCase("fooBar") == false)
    assert(Casing.isSnakeCase("FOO_BAR") == false)
  }

  test("CONSTANT_CASE") {
    assertEquals(Casing.constantCase("foo bar"), "FOO_BAR")
    assertEquals(Casing.constantCase("FooBar"), "FOO_BAR")
    assertEquals(Casing.constantCase("FooBar123"), "FOO_BAR_123")
    assertEquals(
      Casing.constantCase("FooBar123", options = SplitOptions(numbers = false)),
      "FOO_BAR123"
    )
  }

  test("isConstantCase") {
    assert(Casing.isConstantCase("FOO_BAR"))
    assert(Casing.isConstantCase("fooBar") == false)
    assert(Casing.isConstantCase("foo_bar") == false)
  }

  test("kebab-case") {
    assertEquals(Casing.kebabCase("foo bar"), "foo-bar")
    assertEquals(Casing.kebabCase("FooBar"), "foo-bar")
    assertEquals(Casing.kebabCase("FooBar123"), "foo-bar-123")
    assertEquals(
      Casing.kebabCase("FooBar123", options = SplitOptions(numbers = false)),
      "foo-bar123"
    )
  }

  test("isKebabCase") {
    assert(Casing.isKebabCase("foo-bar"))
    assert(Casing.isKebabCase("FOO-BAR") == false)
    assert(Casing.isKebabCase("FOO_BAR") == false)
    assert(Casing.isKebabCase("fooBar") == false)
  }
}
