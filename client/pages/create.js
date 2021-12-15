
//const API_URL = process.env.API_URL

const Create = () => {
    const createTask = async event => {
        event.preventDefault()
        const res = await fetch(`http://127.0.0.1:8080/tasks`, {
            body: JSON.stringify({
                title: event.target.title.value
            }),
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST'
        })
        console.log(res)
    }
    return (
        <form onSubmit={createTask}>
            <label htmlFor="title">Title</label>
            <input type="text" id="title" required/>
            <button type="submit">Create</button>
            
        </form>
    )
}

export default Create
