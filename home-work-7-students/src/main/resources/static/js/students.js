const studentTableBody = document.getElementById('studentTableBody')
const modalTitle = document.getElementById('modalTitle')
const btnCloseModal = document.getElementById('btnCloseModal')
const inputName = document.getElementById('name')
const inputAge = document.getElementById('age')
let studentId

function initPageStudents() {
    sendRequestStudents().then((data) => updatePageStudents(data))
}

function updatePageStudents(students) {
    studentTableBody.innerHTML = ''
    students.forEach(student => studentTableBody.innerHTML += createTableRowStudent(student))
}

function createTableRowStudent(student) {
    return `<tr id="rowStudent${student.id}">
                  <th scope="row">${student.id}</th>
                  <td id="studentName${student.id}">${student.name}</td>
                  <td id="studentAge${student.id}">${student.age}</td>
                  <td>
                     <div class="btn-group" role="group">
                        <button 
                            type="button" 
                            class="btn btn-primary btn-sm px-3"
                            data-mdb-toggle="modal" 
                            data-mdb-target="#staticBackdrop"
                            onclick="createStudentEditingForm(${student.id})"
                            ><i class="fas fa-pen-fancy"></i>
                        </button>
                        <button 
                            type="button" 
                            class="btn btn-danger btn-sm px-3"
                            onclick="deleteStudent(${student.id})"
                            ><i class="fas fa-times"></i>
                        </button>
                     </div>
                  </td>
                </tr>`
}

function updateRowStudent(student) {
    if (document.getElementById('rowStudent' + student.id)) {
        document.getElementById('studentName' + student.id).innerHTML = student.name
        document.getElementById('studentAge' + student.id).innerHTML = student.age

    } else {
        studentTableBody.innerHTML += createTableRowStudent(student)
    }
}

function removeRowStudentById(id) {
    document.getElementById('rowStudent' + id).remove()
}

function initDangerRowStudentById(id) {
    document.getElementById('rowStudent' + id).classList.add('bg-danger')
}

function createStudentEditingForm(id) {
    sendRequestStudentById(id).then(data => {
        modalTitle.innerHTML = 'Редактирование записи'
        studentId = data.id
        inputName.value = data.name
        inputAge.value = data.age
    })
}

function createStudentAddForm() {
    modalTitle.innerHTML = 'Добавление записи'
    studentId = null
    inputName.value = null
    inputAge.value = null
}

function saveStudent() {
    let body = {
        id: studentId,
        name: inputName.value,
        age: inputAge.value
    }
    sendRequestSaveStudent(body).then((data) => {
        updateRowStudent(data)
        btnCloseModal.click()
    })
}

function deleteStudent(id) {
    fetch('/api/student/' + id, {
        method: 'DELETE'
    }).then(response => {
            if (response.ok) {
                removeRowStudentById(id)
            } else {
                initDangerRowStudentById(id)
            }
        }
    )
}

function sendRequestStudents() {
    return fetch('/api/student')
        .then(response => {
            return response.json();
        })
}

function sendRequestStudentById(id) {
    return fetch('/api/student/' + id)
        .then(response => {
            return response.json();
        })
}

function sendRequestSaveStudent(body) {
    return fetch('/api/student', {
        method: "POST",
        body: JSON.stringify(body),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        return response.json()
    })
}









