import {Component, OnInit} from '@angular/core';
import {StudentService} from "../services/student.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Student} from "../services/student";

@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrls: ['./student-form.component.scss']
})
export class StudentFormComponent implements OnInit {

  public student = new Student(null, '', '', null)

  constructor(private studentService: StudentService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      if (param['id'] !== 'new') {
        this.studentService.findById(param['id'])
          .subscribe(student => {
            this.student = student
          }, err => {
            console.error(err)
          })
      }
    })
  }

  submit(): void {
    this.studentService.save(this.student)
      .subscribe(student => {
        this.student = student
      }, err => {
        console.error(err)
      })
  }


}
