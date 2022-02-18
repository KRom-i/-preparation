import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Student} from "./student";

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(public http: HttpClient) {
  }

  public findAll() {
    return this.http.get<Student[]>('api/v1/student/all')
  }

  public findById(id: number) {
    return this.http.get<Student>(`api/v1/student/${id}/id`)
  }

  public save(student: Student){
    if (student.id){
      return this.http.put<Student>('api/v1/student', student)
    }
    return this.http.post<Student>('api/v1/student', student)
  }

  public delete(id: number): void{
    this.http.delete(`api/v1/student/${id}/id`)
  }
}
