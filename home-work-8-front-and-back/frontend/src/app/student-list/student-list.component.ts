import {Component, OnInit} from '@angular/core';
import {StudentService} from "../services/student.service";
import {Student} from "../services/student";

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.scss']
})
export class StudentListComponent implements OnInit {

  students: Student[] = []

  constructor(private studentService: StudentService) {
  }

  ngOnInit(): void {
    this.studentService.findAll()
      .subscribe(data => this.students = data, err => console.error(err))
  }

  removeStudent(id: number | null): void {
    if (id){
      this.studentService.delete(id)
    }
  }

}
