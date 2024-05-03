import axios from "axios";
import React, { useEffect, useState } from "react";
import StudentNav from "./StudentNav";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

const Location = () => {
  const navigate = useNavigate();
  const [locations, setLocations] = useState([]);
  const [universities, setUniversies] = useState([]);
  const [checkbox, setCheckBox] = useState(null);

  const addToList = (id) => {
    if (checkbox.has(id)) {
      checkbox.delete(id);
    } else {
      checkbox.add(id);
    }
  };

  const submit = async () => {
    try {
      const res = await axios.post(
        `http://localhost:8080/api/v1/scholarshipData`,
        { universities: Array.from(checkbox) }
      );
      console.log(res.data,"datata")
      localStorage.setItem("data",JSON.stringify(res.data))
      navigate(`/viewscholarships`);
    } catch (error) {
      toast.error(error);
    }
  };

  const showLocations = async () => {
    const res = await axios.get(`http://localhost:8080/api/v1/locations`);
    setLocations(res.data);
  };

  const showUniversities = async (item) => {
    // checkbox.clear(); //clearing all the values in set
    const res = await axios.get(
      `http://localhost:8080/api/v1/getByLocation/${item}`
    );
    // changing the state of that set
    setUniversies(res.data);
  };

  useEffect(() => {
    if (checkbox) {
      checkbox.clear();
      setCheckBox(checkbox);
    } else {
      setCheckBox(new Set());
    }
  }, [universities]);

  return (
    <div className="bg">
      <StudentNav />
      <div className="grid grid-cols-12 grid-flow-col gap-1 mt-5">
        <div className="col-start-2 col-end-3">
          <div className="w-64 flex justify-center">
            <div
              className="bg-white shadow-md px-7 pt-6 pb-8 mb-4"
              onClick={() => showLocations()}
            >
              <img src="data:image/jpeg;base64,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" />
              <div className="text-lime-700 text-2xl block w-full select-none rounded-lg bg-blue-200  py-3 px-6 text-center align-middle font-sans text-xs font-bold uppercase  shadow-md shadow-slate-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none">
                Select Location
              </div>
            </div>
          </div>
        </div>
        <div className="col-start-5 col-end-6 mt-10">
          <ul className="">
            {locations.map((item, index) => {
              return (
                <li
                  key={index}
                  href="#!"
                  aria-current="true"
                  onClick={() => showUniversities(item)}
                  className="w-36 rounded-lg bg-sky-500  text-sdark p-4 mb-4 text-primary-600"
                >
                  {item}
                </li>
              );
            })}
          </ul>
        </div>

        <div className="col-start-8 col-end-12 mt-10 ">
          {universities.map((item) => {
            return (
              <div key={item.id}>
                <div className="block rounded-lg bg-gradient-to-l from-pink-300 to-blue-600 p-6 shadow-[0_2px_15px_-3px_rgba(0,0,0,0.07),0_10px_20px_-2px_rgba(0,0,0,0.04)] dark:bg-neutral-700 mb-2">
                  <div className="mb-[0.125rem] block min-h-[1.5rem] pl-[1.5rem]">
                    <input
                      className="relative bg-blue-500 float-left -ml-[1.5rem] mr-[6px] mt-[0.15rem] h-[1.125rem] w-[1.125rem] appearance-none rounded-[0.25rem] border-[0.125rem] border-solid border-neutral-300 outline-none before:pointer-events-none before:absolute before:h-[0.875rem] before:w-[0.875rem] before:scale-0 before:rounded-full before:bg-transparent before:opacity-0 before:shadow-[0px_0px_0px_13px_transparent] before:content-[''] checked:border-primary checked:bg-primary checked:before:opacity-[0.16] checked:after:absolute checked:after:-mt-px checked:after:ml-[0.25rem] checked:after:block checked:after:h-[0.8125rem] checked:after:w-[0.375rem] checked:after:rotate-45 checked:after:border-[0.125rem] checked:after:border-l-0 checked:after:border-t-0 checked:after:border-solid checked:after:border-white checked:after:bg-transparent checked:after:content-[''] hover:cursor-pointer hover:before:opacity-[0.04] hover:before:shadow-[0px_0px_0px_13px_rgba(0,0,0,0.6)] focus:shadow-none focus:transition-[border-color_0.2s] focus:before:scale-100 focus:before:opacity-[0.12] focus:before:shadow-[0px_0px_0px_13px_rgba(0,0,0,0.6)] focus:before:transition-[box-shadow_0.2s,transform_0.2s] focus:after:absolute focus:after:z-[1] focus:after:block focus:after:h-[0.875rem] focus:after:w-[0.875rem] focus:after:rounded-[0.125rem] focus:after:content-[''] checked:focus:before:scale-100 checked:focus:before:shadow-[0px_0px_0px_13px_#3b71ca] checked:focus:before:transition-[box-shadow_0.2s,transform_0.2s] checked:focus:after:-mt-px checked:focus:after:ml-[0.25rem] checked:focus:after:h-[0.8125rem] checked:focus:after:w-[0.375rem] checked:focus:after:rotate-45 checked:focus:after:rounded-none checked:focus:after:border-[0.125rem] checked:focus:after:border-l-0 checked:focus:after:border-t-0 checked:focus:after:border-solid checked:focus:after:border-white checked:focus:after:bg-transparent dark:border-neutral-600 dark:checked:border-primary dark:checked:bg-primary dark:focus:before:shadow-[0px_0px_0px_13px_rgba(255,255,255,0.4)] dark:checked:focus:before:shadow-[0px_0px_0px_13px_#3b71ca]"
                      type="checkbox"
                      value={item.id}
                      defaultChecked={() => checkbox.has(item.id)}
                      onChange={(e) => addToList(e.target.value)}
                    />
                  </div>
                  <h5 className="mb-2 text-xl font-medium leading-tight text-neutral-800 dark:text-neutral-50">
                    {item.name}
                  </h5>
                  <p className="mb-4 text-base text-neutral-600 dark:text-neutral-200 text-red-500 font-bold">
                    {item.location}
                  </p>
                </div>
              </div>
            );
          })}
          <button
            type="submit"
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
            data-te-ripple-init
            data-te-ripple-color="light"
            onClick={() => submit()}
          >
            Get Scholorship Details
          </button>
        </div>
      </div>
    </div>
  );
};

export default Location;
