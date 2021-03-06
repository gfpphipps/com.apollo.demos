package com.apollo.demos.osgi.base.impl.shell

import org.junit.Assert._
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

import com.apollo.demos.osgi.base.impl.TestBase

class ShellHelperTest extends TestBase {
  @Test def testTable_RowDataIsEmpty() {
    val columnHeader = List(("Column-01", 2), ("Column-02", 2), ("Column-03", 2))
    val rowData = List()
    val actual = ShellHelper.table(columnHeader, rowData)
    val expected = "\n" +
      "Total number is 0.\n" +
      "----------------------------------------\n" +
      "| Column-01  | Column-02  | Column-03  |\n" +
      "----------------------------------------\n"
    //这里不能使用"""语法，因为"""在scala编译时会有平台差异性，而table输出的是固定的格式\n，windows下"""会把换行识别为\r\n。
    //注意："""在scala编译时就进行平台识别转换控制字符的动作，而不是运行时。所以这个"""的用法需要特别注意，如果编译平台和运行平台不一致很容易引起问题。
    assertEquals(expected, actual)
  }
  @Test def testTable() {
    val columnHeader = List(("Column-01", 2), ("Column-02", 1), ("Column-03", 3))
    val rowData = List(List("This is row 1 cell 1.", 12, 13), List("21\t\t\t\t\t", 22, "\r23\\nok!"))
    val actual = ShellHelper.table(columnHeader, rowData)
    val expected = "\n" +
      "Total number is 2.\n" +
      "----------------------------------------\n" +
      "| Column-01  | ... | Column-03         |\n" +
      "----------------------------------------\n" +
      "| This is... | 12  | 13                |\n" +
      """| 21\t\t\... | 22  | \r23\nok!         |""" + "\n" +
      "----------------------------------------\n"
    assertEquals(expected, actual)
  }

  @Test def testList() {
    val actual = ShellHelper.list("argument-1", 2, "3", "argument-4!!!!!!!!!!!!!!!!!", 5, 6, "argument-7\t\t\t\t\t\t\t\t\t", "\rargument-8\nok!")
    val expected = "\n" +
      "Total number is 8.\n" +
      "------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
      "argument-1                 2                          3                          argument-4!!!!!!!!!!!!!... 5                          6                          \n" +
      """argument-7\t\t\t\t\t\t\... \rargument-8\nok!          """ + "\n"
    assertEquals(expected, actual)
  }

  @Test def testCell() {
    assertEquals("text         ", ShellHelper.cell("text", 0, 3, 2))
    assertEquals("text         \n", ShellHelper.cell("text", 2, 3, 2))
  }

  @Test def testTab() {
    assertEquals("text  ", ShellHelper.tab("text", 1))
    assertEquals("text         ", ShellHelper.tab("text", 2))
    assertEquals("text!!!!!!!! ", ShellHelper.tab("text!!!!!!!!", 2))
    assertEquals("text!!!!!... ", ShellHelper.tab("text!!!!!!!!!", 2))
    assertEquals("""\rtext\t\... """.stripMargin, ShellHelper.tab("\rtext\t\nok!", 2))
  }

  @Test def testLine() {
    val actual = ShellHelper.line("\rtext\t\t\t\nok!")
    val expected = """\rtext\t\t\t\nok!"""
    assertEquals(expected, actual)
  }
}
