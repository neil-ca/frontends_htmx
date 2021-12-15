
//const API_URL = process.env.API_URL

const Create = () => {
    const createTask = async event => {
        event.preventDefault()
        const res = await fetch(`http://127.0.0.1:8080/tasks`, {
            body: JSON.stringify({
                title: event.target.title.value,
                description: event.target.description.value,
                status: event.target.status.value,
                limit_date: event.target.limit_date.value,
                owner: event.target.owner.value,
                tag: event.target.tag.value
            }),
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST'
        })
        alert("Task created")
    }
    return (
        <form onSubmit={createTask}>
            <label htmlFor="title">Title</label>
            <input type="text" id="title" required/>
            
            <label htmlFor="description">Description</label>
            <input type="text" id="description" required/>

            <label htmlFor="status">Status</label>
            <select id="status">
                <option value="1">Pending</option>
                <option value="2">In progress</option>
                <option value="3">Done</option>
            </select> 

            <label htmlFor="limit_date">Deadline</label>
            <input type="text" id="limit_date" required/>

            <label htmlFor="owner">Owner</label>
            <input type="text" id="owner" required/>

            <label htmlFor="tag">Tag</label>
            <input type="text" id="tag" required/>

            <button type="submit">Create</button>
        </form>
    )
}

export default Create
