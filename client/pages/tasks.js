export const getStaticProps = async() => {
    const res = await fetch("http://localhost:8080/tasks/list")
    const tasks = await res.json()
    console.log(tasks)
    return {
        props: { tasks },
        revalidate: 10
    }
}

const Tasks = (props) => {
    const { tasks } = props
    return <div>
        <h1>List of my tasks</h1>
        <ul>
         {tasks.map((task) => {

            return <li key={task.id}>
                <h1>{task.title}</h1>
                 <h2>{task.description}</h2>
                 <h2>{task.owner}</h2>
            </li>
         })}
        </ul>
    </div>   
}

export default Tasks
