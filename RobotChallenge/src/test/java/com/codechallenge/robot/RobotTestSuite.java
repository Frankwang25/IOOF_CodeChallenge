package com.codechallenge.robot;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.codechallenge.testcase.IncorrectCommand;
import com.codechallenge.testcase.MultiRobotMoveConflict;
import com.codechallenge.testcase.MultiRobotNotFound;
import com.codechallenge.testcase.MultiRobotPlaceConflict;
import com.codechallenge.testcase.MultiRobotReport;
import com.codechallenge.testcase.MultiRobotSwitch;
import com.codechallenge.testcase.SingleRobotFalling;
import com.codechallenge.testcase.SingleRobotMove;
import com.codechallenge.testcase.SingleRobotMoves;
import com.codechallenge.testcase.SingleRobotReport;
import com.codechallenge.testcase.SingleRobotRotation;
import com.codechallenge.testcase.SingleRobotRotations;
import com.codechallenge.testcase.SingleRobotRotationsAndMoves;



/**
 * 
 * RobotTestSuite: Read test data from a json file and pass data into test case
 * @author wangli
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ SingleRobotReport.class, SingleRobotMove.class, SingleRobotMoves.class, SingleRobotRotation.class,
		SingleRobotRotations.class, SingleRobotRotationsAndMoves.class, SingleRobotFalling.class,
		MultiRobotReport.class, MultiRobotSwitch.class, MultiRobotMoveConflict.class, MultiRobotPlaceConflict.class,
		IncorrectCommand.class, MultiRobotNotFound.class })
public class RobotTestSuite {

	private JSONArray caseObjArray;
	private JSONObject caseObject;
	private final String testCasePath = "src/test/resources/TestData.json";

	/**
	 * RobotTestSuite constructor: read json file
	 */
	public RobotTestSuite() {
		JSONParser parser = new JSONParser();
		try {
			FileReader fireReader = new FileReader(testCasePath);
			caseObjArray = (JSONArray) parser.parse(fireReader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSONObject getJSONValues(String caseName) {
		for (Object co : caseObjArray) {
			if (((JSONObject) co).get("caseName").equals(caseName)) {
				caseObject = (JSONObject) co;
			}
		}
		return caseObject;
	}
}
