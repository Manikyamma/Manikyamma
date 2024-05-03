import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import AdminNav from "./AdminNav";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import { toast } from "react-toastify";

const AddCourse = () => {
  const params = useParams();
  console.log(params.id, "id");
  const { register, handleSubmit } = useForm();
  const navigate = useNavigate();
  const [stream, setStream] = useState([]);
  const getStudents = async () => {
      const res = await axios.get(`http://localhost:8080/api/v1/stream/${params.id}`);
      console.log(res.data, "students");
      setStream(res.data);
  };
  useEffect(() => {
    const res = axios
      .get(`http://localhost:8080/api/v1/stream/${params.id}`)
      .then((response) => {
        console.log(response.data, "response");
        setStream(response.data);
      });
  }, []);
  const id=JSON.parse(localStorage.getItem("uId"))
  console.log(stream, "stream");
  return (
    <div>
      <div className="bg">
        <AdminNav />
        <div className="grid grid-cols-12 grid-flow-col gap-4 mt-5">
          <div className="col-start-5 col-end-7">
            <div className="flex justify-center w-96">
              <div className="bg-white shadow-md rounded px-7 pt-4 pb-6">
                <div className="relative flex flex-col rounded-xl bg-transparent bg-clip-border text-gray-700 shadow-none">
                  <h4 className="block font-sans text-blue-500 text-2xl font-semibold leading-snug tracking-normal text-blue antialiased">
                    Enter Course Details
                  </h4>
                  <form
                    onSubmit={handleSubmit(async (data) => {
                      console.log(data, "data");
                      try {
                        const res = await axios.post(
                          `http://localhost:8080/api/v1/course`,
                          data
                        );
                        const addStreamToUniversity = await axios.post(
                          `http://localhost:8080/api/v1/addCourse/${params.id}/${res.data.id}`
                        );
                        console.log(res.data, "data");
                        console.log(
                          addStreamToUniversity.data,
                          "addStreamToUniversity"
                        );

                        toast.success("course added successsfully", {});
                      } catch (err) {
                        console.log(err);
                        toast.error(err.response.data.msg, {});
                      }
                    })}
                    className="mt-8 mb-2 w-80 max-w-screen-lg sm:w-96"
                  >
                    <div className="mb-4 flex flex-col gap-6">
                      <div className="relative h-11 w-full min-w-[200px]">
                        {stream && stream.name == "PG" ? (
                          <select
                            data-te-select-init
                            className="peer h-full w-full rounded-md border border-blue-gray-200 border-t-transparent bg-transparent px-3 py-3 font-sans text-sm font-normal text-blue-gray-700 outline outline-0 transition-all placeholder-shown:border placeholder-shown:border-blue-gray-200 placeholder-shown:border-t-blue-gray-200 focus:border-2 focus:border-pink-500 focus:border-t-transparent focus:outline-0 disabled:border-0 disabled:bg-blue-gray-50"
                            placeholder=""
                            {...register("name")}
                          >
                            <option selected>Choose Option</option>
                            <option value="MBA">MBA</option>
                            <option value="MCA">MCA</option>
                            <option value="MCOM">MCOM</option>
                            <option value="MSc">MSc</option>
                            <option value="MA">MA</option>
                          </select>
                        ) : stream.name == "Btech" ? (
                          <select
                            data-te-select-init
                            className="peer h-full w-full rounded-md border border-blue-gray-200 border-t-transparent bg-transparent px-3 py-3 font-sans text-sm font-normal text-blue-gray-700 outline outline-0 transition-all placeholder-shown:border placeholder-shown:border-blue-gray-200 placeholder-shown:border-t-blue-gray-200 focus:border-2 focus:border-pink-500 focus:border-t-transparent focus:outline-0 disabled:border-0 disabled:bg-blue-gray-50"
                            placeholder=""
                            {...register("name")}
                          >
                            <option selected>Choose Option</option>
                            <option value="CSE">CSE</option>
                            <option value="ECE">ECE</option>
                            <option value="CIV">CIV</option>
                            <option value="CPE">CPE</option>
                            <option value="CSS">CSS</option>
                            <option value="AE">AE</option>
                            <option value="AU">AU</option>
                            <option value="EEE">EEE</option>
                          </select>
                        ) : stream.name == "Degree" ? (
                          <select
                            data-te-select-init
                            className="peer h-full w-full rounded-md border border-blue-gray-200 border-t-transparent bg-transparent px-3 py-3 font-sans text-sm font-normal text-blue-gray-700 outline outline-0 transition-all placeholder-shown:border placeholder-shown:border-blue-gray-200 placeholder-shown:border-t-blue-gray-200 focus:border-2 focus:border-pink-500 focus:border-t-transparent focus:outline-0 disabled:border-0 disabled:bg-blue-gray-50"
                            placeholder=""
                            {...register("name")}
                          >
                            <option selected>Choose Option</option>
                            <option value="BSc">BSc</option>
                            <option value="BCOM">BCOM</option>
                            <option value="BA">BA</option>
                            <option value="BBA">BBA</option>
                          </select>
                        ) : (
                          "No stream selected"
                        )}
                      </div>

                      <button
                        className="mt-1 block w-full select-none rounded-lg bg-violet-400 py-3 px-6 text-center align-middle font-sans text-xs font-bold uppercase text-white shadow-md shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
                        type="submit"
                        data-ripple-light="true"
                      >
                        ADD Course
                      </button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className="flex justify-end">
          <h2 className="text-xl text-black font-bold p-1  border-4 mr-20 rounded-lg">
            <Link to={`/addStream/${id}`}>Go Back</Link>
          </h2>
        </div>
      </div>
    </div>
  );
};

export default AddCourse;
