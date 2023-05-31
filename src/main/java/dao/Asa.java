package dao;

import exception.DataBaseException;

public class Asa {

	public static void main(String[] args) throws DataBaseException{
		UserDao ud = new UserDao();
		System.out.println(ud.findMemoId(1));
		ud.createStudent(15, "akagi", "闇に舞い降りた天才");

	}

}
