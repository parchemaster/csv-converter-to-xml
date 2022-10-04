package convert;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.zeroturnaround.zip.ZipUtil;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Converter {

    public static void parsingXML(String studentIDInput, String studentNameInput, String studentSurnameInput,
                                  String studyIdInput, String studyFieldCodeInput, String studyFieldInput,
                                  String codeInput, String courseNameInput, String typeInput,
                                  String semesterInput, String ayearInput, String yearOfStudyInput,
                                  String resultInput, String dateInput, String teacherInput,
                                  String gradeInput, String creditsRegInput, String creditsObtInput, int index) {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("record");
            doc.appendChild(rootElement);

            // creating "student" chile of record
            Element student = doc.createElement("student");
            rootElement.appendChild(student);

            createNewAttribute(doc, "id", studentIDInput, student);
            createNewAttribute(doc, "name", studentNameInput, student);
            createNewAttribute(doc, "surname", studentSurnameInput, student);

            //creating "study"chile of record
            Element study = doc.createElement("study");
            rootElement.appendChild(study);

            createNewAttribute(doc, "id", studyIdInput, study);
            createNewAttribute(doc, "field_code", studyFieldCodeInput, study);
            createNewAttribute(doc, "field", studyFieldInput, study);

            //creating "course" child of study
            Element course = doc.createElement("course");
            study.appendChild(course);

            createNewAttribute(doc, "code", codeInput, course);
            createNewAttribute(doc, "name", courseNameInput, course);
            createNewAttribute(doc, "type", typeInput, course);
            createNewAttribute(doc, "semester", semesterInput, course);
            createNewAttribute(doc, "ayear", ayearInput, course);
            createNewAttribute(doc, "year_of_study", yearOfStudyInput, course);

            //creating "exam" child of course
            Element exam = doc.createElement("exam");
            course.appendChild(exam);

            createNewAttribute(doc, "result", resultInput, exam);
            createNewAttribute(doc, "date", dateInput, exam);
            createNewAttribute(doc, "teacher", teacherInput, exam);
            createNewAttribute(doc, "grade", gradeInput, exam);
            createNewAttribute(doc, "credits_reg", creditsRegInput, exam);
            createNewAttribute(doc, "credits_obt", creditsObtInput, exam);

            saveXML(doc, studentIDInput, index, "src/main/resources/result/");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createNewAttribute(Document doc, String name, String value, Element parentElement) {
        Element studentId = doc.createElement(name);
        studentId.appendChild(doc.createTextNode(value));
        parentElement.appendChild(studentId);
    }

    public static void parsingMissed(String studentID, String registerDateInputString, String registeredByInput, String retakedCourseCode,
                                     String retakedCourseName, String retakedCourseAcademicYear, String approvedForgivedCourseCode,
                                     String approvedForgivedCourseName, String approvedForgivedCourseAcademicear,
                                     String creditsForEnrolled, String creditsForPassedCourses, int index) {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("missed_record");
            doc.appendChild(rootElement);

            Element study = doc.createElement("study");
            rootElement.appendChild(study);

            Element course = doc.createElement("course");
            study.appendChild(course);

            //creating "exam" child of course
            Element exam = doc.createElement("exam");
            course.appendChild(exam);

            createNewAttribute(doc, "register_date", registerDateInputString, exam);
            createNewAttribute(doc, "registered_by", registeredByInput, exam);

            //creating "exam" child of course
            Element retakedCourse = doc.createElement("retaked_course");
            study.appendChild(retakedCourse);

            createNewAttribute(doc, "code", retakedCourseCode, retakedCourse);
            createNewAttribute(doc, "name", retakedCourseName, retakedCourse);
            createNewAttribute(doc, "ayear", retakedCourseAcademicYear, retakedCourse);

            Element approvedForgivedCourse = doc.createElement("approved_forgived_course");
            study.appendChild(approvedForgivedCourse);

            createNewAttribute(doc, "code", approvedForgivedCourseCode, approvedForgivedCourse);
            createNewAttribute(doc, "name", approvedForgivedCourseName, approvedForgivedCourse);
            createNewAttribute(doc, "ayear", approvedForgivedCourseAcademicear, approvedForgivedCourse);

            createNewAttribute(doc, "credits_enrolled", creditsForEnrolled, course);
            createNewAttribute(doc, "credits_passed", creditsForPassedCourses, course);

            saveXML(doc, studentID + "MISSED", index, "src/main/resources/missedResult/");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveXML(Document doc, String studentIDInput, int index, String savePath) {
        try {
            // write the content into xml file
            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty(OutputKeys.METHOD, "xml");
            tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);

            File newDirectory = new File(savePath);
            if(newDirectory.mkdir()) {
                System.out.println("Your files are saving in " + savePath);
            }

            StreamResult resultXML = new StreamResult(new File(savePath + studentIDInput + "("+ index +")" + ".xml"));
            tf.transform(source, resultXML);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void archiveZIP(String studentId) {
        ZipUtil.pack(new File("src/main/resources/result"), new File("src/main/resources/" + studentId + ".zip"));
        System.out.println("Files are archived");
    }
}
