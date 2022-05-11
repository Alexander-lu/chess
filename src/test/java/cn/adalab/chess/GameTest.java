package cn.adalab.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {
  ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setup() {
    outputStreamCaptor.reset();
    System.setOut(new PrintStream(outputStreamCaptor));
  }


  @Test
  public void test1() throws IOException {
    String inputFilename = "input1.txt";
    String outputFilename = "output1.txt";
    runTest(inputFilename, outputFilename);
  }

  @Test
  public void test2() throws IOException {
    String inputFilename = "input2.txt";
    String outputFilename = "output2.txt";
    runTest(inputFilename, outputFilename);
  }

  @Test
  public void test3() throws IOException {
    String inputFilename = "input3.txt";
    String outputFilename = "output3.txt";
    runTest(inputFilename, outputFilename);
  }

  @Test
  public void test4() throws IOException {
    String inputFilename = "input4.txt";
    String outputFilename = "output4.txt";
    runTest(inputFilename, outputFilename);
  }

  @SuppressWarnings("ConstantConditions")
  private void runTest(String inputFilename, String outputFilename) throws IOException {
    setInput(inputFilename);
    Game.main(new String[0]);
    List<String> actualOutput = Arrays.asList(outputStreamCaptor.toString().trim().split("\\R"));
    List<String> expectedOutput = Arrays.asList(
        Files.readString(new File(getClass().getClassLoader().getResource(outputFilename).getPath()).toPath())
            .trim()
            .split("\\R")
    );
    assertEquals(expectedOutput, actualOutput);
  }

  private void setInput(String filename) {
    System.setIn(getClass().getClassLoader().getResourceAsStream(filename));
  }
}