package com.codechallenge.testcase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import com.codechallenge.RobotChallenge;
import com.codechallenge.robot.RobotTestSuite;

public class SingleRobotMove {

	@Test
	public void test() {

		RobotTestSuite rts = new RobotTestSuite();
		JSONObject caseObject = rts.getJSONValues(getClass().getSimpleName());
		String input = caseObject.get("input").toString().replaceAll(";", "\n");
		String ouput = caseObject.get("output").toString() + "\n";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(byteArrayOutputStream);
		RobotChallenge rc = new RobotChallenge(inputStream, ps);
		rc.controlRobot();
		String outputText = byteArrayOutputStream.toString();
		Assert.assertEquals(outputText, ouput);
	}

}
