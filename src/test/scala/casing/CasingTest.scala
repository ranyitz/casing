import minitest._
import casing.test.TestUtils.expect
import casing._

object CasingTest extends SimpleTestSuite {
  test("split") {
    expect(caseSplit("fooBar"), Seq("foo", "Bar"))
    expect(caseSplit("FooBar"), Seq("Foo", "Bar"))
    expect(caseSplit("FOOBar"), Seq("FOO", "Bar"))
    expect(caseSplit("foo_bar"), Seq("foo", "bar"))
    expect(caseSplit("foo-bar"), Seq("foo", "bar"))
    expect(caseSplit("foo.bar"), Seq("foo", "bar"))
    expect(caseSplit("Foo Bar"), Seq("Foo", "Bar"))
    expect(caseSplit("foo bar"), Seq("foo", "bar"))
    expect(caseSplit("FOO_BAR"), Seq("FOO", "BAR"))
    expect(caseSplit("foo/bar"), Seq("foo", "bar"))
    expect(caseSplit(" foo bar "), Seq("foo", "bar"))
    expect(caseSplit("_foo-Bar.Baz_Qux "), Seq("foo", "Bar", "Baz", "Qux"))
  }

  test("split with numbers=false") {
    expect(caseSplit("fooBar123", SplitOptions(numbers = false)), Seq("foo", "Bar123"))
    expect(caseSplit("123foo bar", SplitOptions(numbers = false)), Seq("123foo", "bar"))
    expect(caseSplit("123.foo.bar", SplitOptions(numbers = false)), Seq("123", "foo", "bar"))
    expect(
      caseSplit("Scala2.13.11", SplitOptions(numbers = false)),
      Seq("Scala2", "13", "11")
    )
    expect(caseSplit("1V", SplitOptions(numbers = false)), Seq("1V"))
  }

  test("split with numbers=true (default)") {
    expect(caseSplit("fooBar123"), Seq("foo", "Bar", "123"))
    expect(caseSplit("123foo bar"), Seq("123", "foo", "bar"))
    expect(
      caseSplit("Scala2.13.11"),
      Seq("Scala", "2", "13", "11")
    )
    expect(caseSplit("1V"), Seq("1", "V"))
  }

  test("camelCase") {
    assertEquals(camelCase("foo bar"), "fooBar")
    assertEquals(camelCase("FOO BAR"), "fooBar")
    assertEquals(camelCase("foo bar 123"), "fooBar123")
  }

  test("isCamelCase") {
    assert(isCamelCase("fooBar"))
    assert(isCamelCase("FooBar") == false)
    assert(isCamelCase("foo_bar") == false)
  }

  test("PascalCase") {
    assertEquals(pascalCase("foo bar"), "FooBar")
    assertEquals(pascalCase("FOO BAR"), "FooBar")
  }

  test("isPascalCase") {
    assert(isPascalCase("FooBar"))
    assert(isPascalCase("fooBar") == false)
    assert(isPascalCase("foo_bar") == false)
  }

  test("snake_case") {
    assertEquals(snakeCase("foo bar"), "foo_bar")
    assertEquals(snakeCase("FooBar"), "foo_bar")
    assertEquals(snakeCase("FooBar123"), "foo_bar_123")
    assertEquals(
      snakeCase("FooBar123", options = SplitOptions(numbers = false)),
      "foo_bar123"
    )
  }

  test("isSnakeCase") {
    assert(isSnakeCase("foo_bar"))
    assert(isSnakeCase("fooBar") == false)
    assert(isSnakeCase("FOO_BAR") == false)
  }

  test("CONSTANT_CASE") {
    assertEquals(constantCase("foo bar"), "FOO_BAR")
    assertEquals(constantCase("FooBar"), "FOO_BAR")
    assertEquals(constantCase("FooBar123"), "FOO_BAR_123")
    assertEquals(
      constantCase("FooBar123", options = SplitOptions(numbers = false)),
      "FOO_BAR123"
    )
  }

  test("isConstantCase") {
    assert(isConstantCase("FOO_BAR"))
    assert(isConstantCase("fooBar") == false)
    assert(isConstantCase("foo_bar") == false)
  }

  test("kebab-case") {
    assertEquals(kebabCase("foo bar"), "foo-bar")
    assertEquals(kebabCase("FooBar"), "foo-bar")
    assertEquals(kebabCase("FooBar123"), "foo-bar-123")
    assertEquals(
      kebabCase("FooBar123", options = SplitOptions(numbers = false)),
      "foo-bar123"
    )
  }

  test("isKebabCase") {
    assert(isKebabCase("foo-bar"))
    assert(isKebabCase("FOO-BAR") == false)
    assert(isKebabCase("FOO_BAR") == false)
    assert(isKebabCase("fooBar") == false)
  }
}
