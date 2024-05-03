
import React, { useState } from "react";
import StudentNav from "./StudentNav";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const StudentHome = () => {
  const [straeams, setStreams] = useState([]);
  const [branches, setBranches] = useState([]);
  const [university, setUniversies] = useState([]);
  const navigate = useNavigate();
  const showStreams = async (id) => {
    const res = await axios.get(`http://localhost:8080/api/v1/getStream/${id}`);
    console.log(res.data, "res");
    setStreams(res.data);
  };
  const showBranches = async (id) => {
    const res = await axios.get(`http://localhost:8080/api/v1/getCourse/${id}`);
    console.log(res.data, "courses");
    setBranches(res.data);
  };
  const showUniversities = async (id) => {
    const res = await axios.get(`http://localhost:8080/api/v1/university`);
    console.log(res.data, "Univees");
    setUniversies(res.data);
  };
  return (
    <div className="bg">
      <StudentNav />
      <div className="grid grid-cols-12 grid-flow-col gap-4">
        <div className="col-start-3 col-end-4 mt-5">
          <div className="w-64 flex justify-center">
          <ul class="w-64 bg-white shadow-md px-7 pt-6 pb-8 mb-4">
            <li class="w-full rounded-lg bg-sky-100 p-4 text-primary-600" onClick={()=>showUniversities()}>

              All Universities
            </li>
            {university.map((item) => {
              return (
                <>
                  <a
                    href="#!"
                    aria-current="true"
                    onClick={() => showStreams(item.id)}
                    class="block w-full cursor-pointer rounded-lg bg-primary-100 p-4 text-primary-600"
                  >
                    {item.name}
                  </a>{" "}
                  <hr />
                </>
              );
            })}
          </ul>
          </div>
        </div>
        <div className="col-start-6 col-end-8 mt-10">
          <ul class="w-64 bg-white shadow-md px-7 pt-6 pb-8 mb-4">
            <li class="w-full rounded-lg bg-sky-100 p-4 text-primary-600">
              All Streams
            </li>
            {straeams.map((item) => {
              return (
                <>
                  <a
                    href="#!"
                    aria-current="true"
                    onClick={() => showBranches(item.id)}
                    class="block w-full cursor-pointer rounded-lg bg-primary-100 p-4 text-primary-600"
                  >
                    {item.name}
                  </a>{" "}
                  <hr />
                </>
              );
            })}
          </ul>
        </div>
        <div className="col-start-9 col-end-11 mt-20">
          <ul class="">
            {/* <li class="w-full rounded-lg bg-blue-100 p-4 text-primary-600">
              All Branches
            </li> */}
            {branches.map((item) => {
              return (
                <>
                  <li
                    class="w-full p-4 w-64 bg-white shadow-md px-7 pt-6 pb-8 mb-4"
                    onClick={() => navigate("/location")}
                  >
                    {item.name}
                  </li>
                  <hr />
                </>
              );
            })}
          </ul>
        </div>
      </div>
    </div>
  );
};

export default StudentHome;


