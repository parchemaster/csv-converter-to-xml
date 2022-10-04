package convert;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Converter {

    public static void CreateXML(String studentIDInput, String studentNameInput, String studentSurnameInput,
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

            Element studentId = doc.createElement("id");
            studentId.appendChild(doc.createTextNode(studentIDInput));
            student.appendChild(studentId);

            Element studentName = doc.createElement("name");
            studentName.appendChild(doc.createTextNode(studentNameInput));
            student.appendChild(studentName);

            Element surname = doc.createElement("surname");
            surname.appendChild(doc.createTextNode(studentSurnameInput));
            student.appendChild(surname);

            //creating "study"chile of record
            Element study = doc.createElement("study");
            rootElement.appendChild(study);

            Element studyId = doc.createElement("id");
            studyId.appendChild(doc.createTextNode(studyIdInput));
            study.appendChild(studyId);

            Element fieldCode = doc.createElement("field_code");
            fieldCode.appendChild(doc.createTextNode(studyFieldCodeInput));
            study.appendChild(fieldCode);

            Element field = doc.createElement("field");
            field.appendChild(doc.createTextNode(studyFieldInput));
            study.appendChild(field);

            //creating "course" child of study
            Element course = doc.createElement("course");
            study.appendChild(course);

            Element code = doc.createElement("code");
            code.appendChild(doc.createTextNode(codeInput));
            course.appendChild(code);

            Element courseName = doc.createElement("name");
            courseName.appendChild(doc.createTextNode(courseNameInput));
            course.appendChild(courseName);

            Element type = doc.createElement("type");
            type.appendChild(doc.createTextNode(typeInput));
            course.appendChild(type);

            Element semester = doc.createElement("semester");
            semester.appendChild(doc.createTextNode(semesterInput));
            course.appendChild(semester);

            Element ayear = doc.createElement("ayear");
            ayear.appendChild(doc.createTextNode(ayearInput));
            course.appendChild(ayear);

            Element yearOfStudy = doc.createElement("year_of_study");
            yearOfStudy.appendChild(doc.createTextNode(yearOfStudyInput));
            course.appendChild(yearOfStudy);

            //creating "exam" child of course
            Element exam = doc.createElement("exam");
            course.appendChild(exam);

            Element result = doc.createElement("result");
            result.appendChild(doc.createTextNode(resultInput));
            exam.appendChild(result);

            Element date = doc.createElement("date");
            date.appendChild(doc.createTextNode(dateInput));
            exam.appendChild(date);

            Element teacher = doc.createElement("teacher");
            teacher.appendChild(doc.createTextNode(teacherInput));
            exam.appendChild(teacher);

            Element grade = doc.createElement("grade");
            grade.appendChild(doc.createTextNode(gradeInput));
            exam.appendChild(grade);

            Element creditsReg = doc.createElement("credits_reg");
            creditsReg.appendChild(doc.createTextNode(creditsRegInput));
            exam.appendChild(creditsReg);

            Element credits_obt = doc.createElement("credits_obt");
            credits_obt.appendChild(doc.createTextNode(creditsObtInput));
            exam.appendChild(credits_obt);


            // write the content into xml file
            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty(OutputKeys.METHOD, "xml");
            tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult resultXML = new StreamResult(new File("src/main/resources/result/" + studentIDInput + "("+ index +")" + ".xml"));
            tf.transform(source, resultXML);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
