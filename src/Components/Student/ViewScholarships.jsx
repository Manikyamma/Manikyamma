import React from "react";
import { Link } from "react-router-dom";
import { Ripple, initTE } from "tw-elements";
import StudentNav from "./StudentNav";
const ViewScholarships = () => {
  initTE({ Ripple });

  const data = JSON.parse(localStorage.getItem("data"));

  return (
    <div>
      <StudentNav />
      <div className="flex justify-center mt-3 bg-clip-padding ">
        <h2 className="text-4xl text-black font-bold p-3 border-4 rounded-lg">
          All Scholarships
        </h2>
      </div>
      <div className="flex justify-center gap-5">
        {data.map((item) => {
          return (
            <div className="w-96 ">
              <div className="block mt-10 rounded-lg bg-white p-6 shadow-[0_2px_15px_-3px_rgba(0,0,0,0.07),0_10px_20px_-2px_rgba(0,0,0,0.04)] dark:bg-neutral-700">
                <h5 className="mb-2 text-xl font-medium leading-tight text-neutral-800 dark:text-neutral-50">
                  Scholaship Details
                </h5>
                <div>
                  <h2 className="text-black text-start text-xl font-bold text-fuchsia-500 ">
                    University :
                    <b className="text-rose-800 ml-2">{item.university}</b>
                  </h2>
                  <h2 className="text-black text-start text-xl font-bold text-fuchsia-500 ">
                    Eligibility :
                    <b className="text-rose-800 ml-2">{item.eligibility}</b>
                  </h2>
                  <h2 className="text-black text-start text-xl font-bold text-fuchsia-500 ">
                    Amount(/m) :
                    <b className="text-rose-800 ml-2">{item.amount}</b>
                  </h2>
                  <h2 className="text-black text-start text-xl font-bold text-fuchsia-500 ">
                    Rank :
                    <b className="text-rose-800 ml-2">{item.rank}</b>
                  </h2>
                  <h2 className="text-black text-start text-xl font-bold text-fuchsia-500 ">
                    Description :
                    <b className="text-rose-800 ml-2">{item.description}students of Sc,St are eligible</b>
                  </h2>
                </div>
              </div>
            </div>
          );
        })}
      </div>
      <div className="flex justify-end">
        <h2 className="text-xl text-black font-bold p-1  border-4 mr-20 rounded-lg">
          <Link to={"/location"}>Go Back</Link>
        </h2>
      </div>
    </div>
  );
};

export default ViewScholarships;
