import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router,Routes,Route } from 'react-router-dom';
import Index from './Pages/Index';
import Login from './Pages/Login';
import AdminLogin from './Pages/AdminLogin';
import { ToastContainer, toast } from 'react-toastify';
  import 'react-toastify/dist/ReactToastify.css';
import Register from './Pages/Register';
import StudentHome from './Components/Student/StudentHome';
import Location from './Components/Student/Location';
import Password from './Components/Student/Password';
import ViewScholarships from './Components/Student/ViewScholarships';
import AddStream from './Components/Admin/AddStream';
import AddUniversity from './Components/Admin/AddUniversity';
import AddCourse from './Components/Admin/AddCourse';
import AdminHome from './Components/Admin/AdminHome';
import ViewUniversity from './Components/Admin/ViewUniversity';
import AddScholorship from './Components/Admin/AddScholorship';
import AdminViewScholarships from './Components/Admin/AdminViewScholarships';
function App() {
  return (
    <div className="App">
      <ToastContainer/>
      <Router>
        <Routes>
          <Route path='/' element={<Index/>}/>
          <Route path='/login' element={<Login/>}/>
          <Route path='/adminLogin' element={<AdminLogin/>}/>
          <Route path='/register' element={<Register/>}/>
          <Route path='/studentHome' element={<StudentHome/>}/>
          <Route path='/location' element={<Location/>}/>
          <Route path='/password' element={<Password/>}/>
          <Route path='/viewscholarships' element={<ViewScholarships/>}/>
          <Route path='/addStream/:id' element={<AddStream/>}/>
          <Route path='/addUniversity' element={<AddUniversity/>}/>
          <Route path='/addCourse/:id' element={<AddCourse/>}/>
          <Route path='/adminHome' element={<AdminHome/>}/>
          <Route path='/viewuniversity' element={<ViewUniversity/>}/>
          <Route path='/addscholarship/:id' element={<AddScholorship/>}/>
          <Route path='/adminViewScholarships/:id' element={<AdminViewScholarships/>}/>

        </Routes>
      </Router>
 
    </div>
  );
}

export default App;
