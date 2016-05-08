<?php

namespace App\Http\Controllers;

use App\Http\Requests;
use Auth;
use Illuminate\Foundation\Auth\User;
use Illuminate\Http\Request;
use App\phoneBook;
use Validator;
use Session;
use Faker\Factory as Faker;


class HomeController extends Controller {

    /**
     * Create a new controller instance.
     *
     * @return void
     */
    // Preventing user for accessing wrong table field.
    protected $searchFields = ['id', 'name', 'phone_number', 'additional_notes', 'created_at'];

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $viewData = phoneBook::paginate(10);

        return view('home', ['result' => $viewData]);
    }

    /**
     * Validate Inputs and Update/Add Records
     * @param Request $request
     * @return $this|\Illuminate\Http\RedirectResponse
     */
    public function updateRecord(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'name'         => 'required|max:255',
            'phone_number' => 'required',
        ]);
        if ($validator->fails())
        {
            return \Redirect::back()->withErrors($validator)->withInput();
        } else
        {
            // action is either update or add
            $action = strtolower($request->input('action'));
            // updating existing records, if someone tries to temper data application will halt.
            if ($action === 'update')
            {
                $item                   = phoneBook::findOrFail(intval($request->input('id')));
                $item->name             = $request->input('name');
                $item->phone_number     = $request->input('phone_number');
                $item->additional_notes = $request->input('additional_notes');
                $item->update();
            } else
            {
                $item                   = new phoneBook();
                $item->name             = $request->input('name');
                $item->user_id          = Auth::user()->id;
                $item->phone_number     = $request->input('phone_number');
                $item->additional_notes = $request->input('additional_notes');
                $item->save();
            }

            // Redirecting back to Update/Added page with a flash message.
            return \Redirect::back()->with('flash-message', "Record $action successfully!");
        }
    }

    /**
     * Searching Database against required fields
     * @param Request $request
     * @return \Illuminate\Http\RedirectResponse
     */
    public function searchRecord(Request $request)
    {
        // strtolower because our table fields are lower case.
        $searchField      = strtolower($request->input('search_param'));
        $searchFieldValue = $request->input('search');
        // if input are valid and table field is found in $searchFields then we'll proceed
        if (isset($request) && !empty($request) && in_array($searchField, $this->searchFields))
        {
            $viewData = phoneBook::where($searchField, $searchFieldValue)
                ->paginate(10);
            if (count($viewData) > 0)
            {
                return view('search', ['result' => $viewData, 'searchData' => [$searchField, $searchFieldValue]]);
            } else
            {
                return \Redirect::back()->with('flash-message', "No records found! Try something else.");
            }
        } else
        {
            return \Redirect::back()->with('flash-message', "No records found! Try something else.");
        }
    }

    /**
     * This method will generate fake data and insert into table.
     */
    public function createFakeData()
    {
        $faker = Faker::create();
        foreach (range(1, 100) as $index)
        {
            $user           = new User;
            $user->name     = $faker->name;
            $user->email    = $faker->email;
            $user->password = bcrypt('phonebook');
            $user->save();

            $item                   = new phoneBook();
            $item->name             = $faker->name;
            $item->user_id          = Auth::user()->id;
            $item->phone_number     = $faker->phoneNumber;
            $item->additional_notes = $faker->catchPhrase;
            $item->save();
        }
    }
}
