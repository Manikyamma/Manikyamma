import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { toast } from "react-toastify";
import AdminNav from "./AdminNav";

const AdminViewScholarships = ({ data }) => {
  const [scholarships, setScholarships] = useState([]);
  const { id } = useParams();

  const fetchScholarships = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/v1/university/${id}`
      );

      console.log(response.data, "scholarship data");
      setScholarships(response.data.scholarships);
    } catch (error) {
      console.error("Error fetching scholarship data:", error);
    }
  };

  useEffect(() => {
    fetchScholarships();
  }, [id]);

  const handleDeleteScholarship = async (scholarshipId) => {
    try {
      await axios.delete(`http://localhost:8080/api/v1/delete/scholarship/${id}/${scholarshipId}`);
      console.log("Scholarship deleted successfully!");
        toast.success("Scholarship deleted successfully!");
      fetchScholarships();
    } catch (error) {
      console.error("Error deleting scholarship:", error);
    }
  };

  return (
    <div>
      <AdminNav />
      <div className="flex justify-center mt-3 bg-clip-padding">
        <h2 className="text-4xl text-black font-bold p-3 border-4 rounded-lg">
          All Scholarships
        </h2>
      </div>
      <div className="flex justify-center gap-5">
        {scholarships.map((item, index) => (
          <div key={index} className="w-96">
            <div className="block mt-10 rounded-lg bg-white p-6 shadow-[0_2px_15px_-3px_rgba(0,0,0,0.07),0_10px_20px_-2px_rgba(0,0,0,0.04)] dark:bg-neutral-700">
              <h5 className="mb-2 text-xl font-medium leading-tight text-neutral-800 dark:text-neutral-50">
                Scholarship Details
              </h5>
              <div>
                <h2 className="text-black text-start text-xl font-bold text-fuchsia-500">
                  University:
                  <b className="text-rose-800 ml-2">{item.university}</b>
                </h2>
                <h2 className="text-black text-start text-xl font-bold text-fuchsia-500">
                  Eligibility:
                  <b className="text-rose-800 ml-2">{item.eligibility}</b>
                </h2>
                <h2 className="text-black text-start text-xl font-bold text-fuchsia-500">
                  Amount(/m):
                  <b className="text-rose-800 ml-2">{item.amount}</b>
                </h2>
                <h2 className="text-black text-start text-xl font-bold text-fuchsia-500">
                  Rank:
                  <b className="text-rose-800 ml-2">{item.rank}</b>
                </h2>
                <h2 className="text-black text-start text-xl font-bold text-fuchsia-500">
                  Description:
                  <b className="text-rose-800 ml-2">{item.description}</b>
                </h2>
                <div className="flex justify-end mt-4 space-x-4">
                  <button
                    className="px-4 py-2 bg-red-500 text-white rounded-lg"
                    onClick={() => handleDeleteScholarship(item.id)}
                  >
                    Delete
                  </button>
                  {/* <Link
                    to={`/editScholarship/${item.id}`}
                    className="px-4 py-2 bg-blue-500 text-white rounded-lg"
                  >
                    Edit
                  </Link> */}
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
      <div className="flex justify-end">
        <h2 className="text-xl text-black font-bold p-1 border-4 mr-20 rounded-lg">
          <Link to={"/viewUniversity"}>Go Back</Link>
        </h2>
      </div>
    </div>
  );
};

export default AdminViewScholarships;
