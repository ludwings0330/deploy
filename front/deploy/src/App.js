import logo from './logo.svg';
import './App.css';
import {useEffect, useState} from "react";

function App() {
  const [name, setName] = useState("안뇽")
  const [address, setAddress] = useState("")
  const [phone, setPhone] = useState("")
  const [members, setMembers] = useState([])
  const saveName = (e) => {
    setName(e.target.value)
    console.log(name)
  }

  const saveAddress = (e) => {
    setAddress(e.target.value)
  }

  const savePhone = (e) => {
    setPhone(e.target.value)
  }

  const join = async (e) => {
    await fetch("http://localhost:8081/api/members", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name,
        address,
        phone
      })
    }).then(r => console.log(r)).catch(e => console.log(e))

    getMembers()
  }

  const getMembers = () => {
    console.log("get members call")
    fetch("http://localhost:8081/api/members", {
      method: "GET"
    }).then(r => r.json())
    .then(json => setMembers(json)).catch(e => console.log(e))
  }

  const deleteMember = (id) => {
    console.log("deleteMember called")
    fetch(`http://localhost:8081/api/members/${id}`, {
      method: "DELETE"
    }).then(r => {
      console.log(r)
      getMembers()
    })

  }

  useEffect(() => {
    getMembers()
  }, [])

  return (
    <div className="App">
      <header className="App-header">
        <div>
          name <input type="text" value={name} onChange={ saveName } />
          address <input type="text" value={address} onChange={ saveAddress }/>
          phone <input type="text" value={phone} onChange={savePhone}/>
          <button onClick={join}>submit</button>
        </div>
        <div>
          <button onClick={getMembers}>get members</button>
        </div>
        { members.map(member => (
            <div onClick={ () => deleteMember(member.id) } key={member.id}>
              {`${member.name} / ${member.address} / ${member.phone}`}
            </div>
        ))}
      </header>
    </div>
  );
}

export default App;
