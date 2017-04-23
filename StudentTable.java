package studentSearch;


import java.util.List;

import javax.swing.table.AbstractTableModel;

import student.MainStudent;

class StudentTable extends AbstractTableModel {

	public static final int OBJECT_COL = -1;
	private static final int LAST_NAME_COL = 0;
	private static final int FIRST_NAME_COL = 1;
	private static final int EMAIL_COL = 2;
	private static final int COURSE_COL = 3;

	private String[] columnNames = { "Last Name", "First Name", "Email",
			"Course" };
	private List<MainStudent> studnet;

	public StudentTable(List<MainStudent> thestudent) {
		studnet = thestudent;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return studnet.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		MainStudent tempStudnet = studnet.get(row);

		switch (col) {
		case LAST_NAME_COL:
			return tempStudnet.getLastName();
		case FIRST_NAME_COL:
			return tempStudnet.getFirstName();
		case EMAIL_COL:
			return tempStudnet.getEmail();
		case COURSE_COL:
			return tempStudnet.getCourse();
		case OBJECT_COL:
			return tempStudnet;
		default:
			return tempStudnet.getLastName();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
