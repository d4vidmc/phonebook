<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/
// added phoneBook model 
use App\phoneBook;
use App\Http\Requests;

// main home route if user is logged in then redirect user to home page
Route::get('/', function ()
{
    if(Auth::user()){
        return redirect('/home');
    }
    return view('welcome');
});

Route::auth();

// grouping routes with laravel auth middleware, un-authenticated user is unable to see of the dashboard pages.
Route::group(/**
 *
 */
    ['middleware' => 'auth'], function ()
{
    // Main home route to display all results
    Route::get('/home', 'HomeController@index');

     // Edit rote accepts id, if id is not found or someone try to access an id which is not in our database application will throw an error.
    Route::get('/edit/{id}', function ($id)
    {
        $item = phoneBook::findOrFail($id);

        return view('edit', ['item' => $item, 'id' => $id, 'action' => 'Update']);
    });

    // Route for adding a new entry
    Route::get('/add-note', function ()
    {
        return view('edit', ['action' => 'Add']);
    });

    // Delete record and redirect to main page with a flash message.
    Route::get('/delete/{id}', function ($id)
    {
        $item = phoneBook::findOrFail($id);
        $item->delete();

        return redirect('/home')->with('flash-message', "Record number $id deleted successfully!");
    });
    // Post Route for updating/Adding new/previous entries.
    Route::post('/update_record', 'HomeController@updateRecord');
    // search route is same as home route we're just handling search related items.
    Route::any("/search", 'HomeController@searchRecord');

    //Run this route for generating fake data. you can use any email address with the password "phonebook"
    Route::get("/fake-data", 'HomeController@createFakeData');
});