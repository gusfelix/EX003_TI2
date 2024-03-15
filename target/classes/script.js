//passando os elementos como objetos
const formAdd = document.getElementById('formAdd')
const formEditTask = document.getElementById('formEditTask')

//botoes
const btnCreate = document.getElementById('btnCreate')
const btnEditTask = document.getElementById('btnEditTask')
const btnDeleteTask = document.getElementById('btnDeleteTask')

//campos form adicionar
const atomicNumInput = document.getElementById('atomicNum')
const elementNameInput = document.getElementById('elementName')
const symbolInput = document.getElementById('symbol')
const familyInput = document.getElementById('family')

//campos form editar
const idEditInput = document.getElementById('idEditInput')
const titleEditInput = document.getElementById('titleEditInput')
const descriptionEditInput = document.getElementById('descriptionEditInput')
const dateEditInput = document.getElementById('dateEditInput')
const priorityEditInput = document.getElementById('priorityEditInput')
const statusEditInput = document.getElementById('statusEditInput')
const lateEditInput = document.getElementById('lateEditInput')

//campo form deletar
const deleteId = document.getElementById('inputDeleteId')

//chamar funcao de criar tarefa ao clicar no botao
btnCreate.addEventListener('click', event => {

    // event.preventDefault()

    if (!formAdd.checkValidity()) {

        checkInputValid(atomicNumInput)
        checkInputValid(elementNameInput)
        checkInputValid(symbolInput)

        return
    }




    formAdd.reset()
    // $('#modalAddTask').modal('toggle')

    //rolar até o fim da página para mostrar a nova tarefa adicionada
    setTimeout(() => { 
        readTasks()
        window.scrollTo(0, document.body.scrollHeight)
    }, 2000)

})

//chamar funcao de atualizar tarefa ao clicar no botao
btnEditTask.addEventListener('click', event => {

    event.preventDefault()

    if (!formEditTask.checkValidity()) {

        checkInputValid(titleEditInput)
        checkInputValid(descriptionEditInput)
        checkInputValid(dateEditInput)

        return
    }

    let task = {
        title: returnValeuById('titleEditInput'),
        description: returnValeuById('descriptionEditInput').replace(/(\r\n|\n|\r)/gm,""),
        start: returnValeuById('dateEditInput'),
        priority: returnValeuById('priorityEditInput'),
        status: returnValeuById('statusEditInput'),
        late: returnValeuById('lateEditInput')
    }

    let id = parseInt(returnValeuById('idEditInput'))

    updateTask(id, task)

    formAddTask.reset()
    $('#modalEditTask').modal('toggle')

    //atualizar a tabela da dados
    setTimeout(() => { 
        readTasks()
    }, 2000)

})

//chamar funcao de deletar tarefa ao clicar no botao
btnDeleteTask.addEventListener('click', () => {

    let id = parseInt(returnValeuById('inputDeleteId'))

    deleteTask(id)

    $('#modalDeleteTask').modal('toggle')

    //atualizar a tabela da dados
    setTimeout(() => { 
        readTasks()
    }, 2000)
})


//retornar o valor de um elemento pelo id
const returnValeuById = id => document.getElementById(id).value

//adicionar ou removar classe invalida
function checkInputValid(input) {

    if (input.value == '')
        input.classList.add('is-invalid')
    else
        input.classList.remove('is-invalid')
}

//funcoes para exibir mensagens na tela
function displaySuccsMessage(mensagem) {
    msg = document.getElementById('msg');
    msg.innerHTML =
        `<div class="alert alert-success alert-dismissible fade show" role="alert">
        ${mensagem}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>`;
}

function displayErrMessage(mensagem) {
    msg = document.getElementById('msg');
    msg.innerHTML =
        `<div class="alert alert-danger alert-dismissible fade show" role="alert">
        ${mensagem}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>`;
}


//funcoes para exibir dados e modais na tela
function showTasks(tasks) {

    const table = document.getElementById('tasksTable');
    let content = '';

    tasks.forEach(task => {

    content +=
    `<tr>
        <td>${task.id}</td> 
        <td>${task.title}</td> 
        <td>${task.description}</td> 
        <td>${formatDate(task.start)}</td> 
        <td>${formartPriority(task.priority)}</td> 
        <td>${formartStatus(task.status)}</td>
        <td class="text-center"><i class="bi bi-pencil" title="Editar" 
        onclick="showTask(${task.id}, '${task.title}', '${task.description}','${task.start}','${task.priority}', ${task.status}, '${task.late}')">
        </i></td> 

        <td class="text-center"><i class="bi bi-trash" title="Excluir"
            onclick="deleteConfirm(${task.id})"></i>
        </td>  
    </tr>`

    })

    table.innerHTML = content
}

function showTask(id, title, description, start, priority, status, late) {

    idEditInput.value = id
    titleEditInput.value = title
    descriptionEditInput.value = description
    dateEditInput.value = start
    priorityEditInput.value = priority
    statusEditInput.value = status
    lateEditInput.value = late

    $('#modalEditTask').modal('toggle')
}

function deleteConfirm(id) {

    deleteId.value = id;
    $('#modalDeleteTask').modal('toggle')
}
