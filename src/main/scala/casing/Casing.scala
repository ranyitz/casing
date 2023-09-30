package casing

import scala.util.matching.Regex

class Casing {
  // finds a lower char followed by an upper char and returns them as capture groups
  // e.g. fooBar -> foo, Bar
  val LOWER_UPPER_REGEX: Regex = "([\\p{Ll}])(\\p{Lu})".r
  // finds an upper char followed by and upper and lower char and return them as capture groups
  // e.g. FOOBar -> FOO, Bar
  val UPPER_UPPER_LOWER_REGEX: Regex = "(\\p{Lu})([\\p{Lu}][\\p{Ll}])".r
  // finds a number followed by a letter and returns them as capture groups
  val NUMBER_LETTER_REGEX: Regex = "(\\d)(\\p{L})".r
  // finds a letter followed by a number and returns them as capture groups
  val LETTER_NUMBER_REGEX: Regex = "(\\p{L})(\\d)".r
  // finds all non-alphanumeric characters (including non-ASCII)
  val NON_ALPHANUMERIC_REGEXP: Regex = "[^\\p{L}\\d]+".r
  // use a null character as a delimeter
  val DELIMETER = "\u0000"
  // a regex replacement value that will be used to replace the matched value with
  // the first capture group followed by a delimeter and the second capture group
  val REPLACEMENT_SEPARATOR: String = s"$$1$DELIMETER$$2"

  def split(input: String, options: SplitOptions = SplitOptions()): Seq[String] = {
    var result = input
      .replaceAll(LOWER_UPPER_REGEX.pattern.pattern(), REPLACEMENT_SEPARATOR)
      .replaceAll(UPPER_UPPER_LOWER_REGEX.pattern.pattern(), REPLACEMENT_SEPARATOR)

    if (options.numbers) {
      result = result
        .replaceAll(NUMBER_LETTER_REGEX.pattern.pattern(), REPLACEMENT_SEPARATOR)
        .replaceAll(LETTER_NUMBER_REGEX.pattern.pattern(), REPLACEMENT_SEPARATOR)
    }

    result = result.replaceAll(NON_ALPHANUMERIC_REGEXP.pattern.pattern(), DELIMETER)

    result.split(DELIMETER).filter(_.nonEmpty)
  }

  // foo bar -> fooBar
  def camelCase(input: String): String = {
    split(input).zipWithIndex.map { case (word, index) =>
      if (index == 0) word.toLowerCase
      else word.toLowerCase.capitalize
    }.mkString
  }

  def isCamelCase(input: String): Boolean = {
    camelCase(input) == input
  }

  // foo bar -> FooBar
  def pascalCase(input: String): String = {
    split(input).map(_.toLowerCase.capitalize).mkString
  }

  def isPascalCase(input: String): Boolean = {
    pascalCase(input) == input
  }

  // foo bar -> foo_bar
  def snakeCase(input: String, options: SplitOptions = SplitOptions()): String = {
    split(input, options).map(_.toLowerCase).mkString("_")
  }

  def isSnakeCase(input: String, options: SplitOptions = SplitOptions()): Boolean = {
    snakeCase(input, options) == input
  }

  // foo bar -> FOO_BAR
  def constantCase(input: String, options: SplitOptions = SplitOptions()): String = {
    split(input, options).map(_.toUpperCase).mkString("_")
  }

  def isConstantCase(input: String, options: SplitOptions = SplitOptions()): Boolean = {
    constantCase(input, options) == input
  }

  // foo bar -> foo-bar
  def kebabCase(input: String, options: SplitOptions = SplitOptions()): String = {
    split(input, options).map(_.toLowerCase).mkString("-")
  }

  def isKebabCase(input: String, options: SplitOptions = SplitOptions()): Boolean = {
    kebabCase(input, options) == input
  }
}

object Casing extends Casing

case class SplitOptions(numbers: Boolean = true)
