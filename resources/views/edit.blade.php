@extends('layouts.app')
@section('content')
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="panel panel-default">
                    <div class="panel-heading">Dashboard</div>
                    <div class="panel-body">
                        @if(Session::get('flash-message'))
                            <div class="alert alert-success" role="alert">
                                {{Session::get('flash-message')}}
                            </div>
                        @endif
                        <form class="form-horizontal" role="form" method="POST" action="{{ url('/update_record') }}">
                            {!! csrf_field() !!}
                            @if(isset($id))
                                <input type="hidden" name="id" value="{{ $id }}"/>
                            @endif
                            <input type="hidden" name="action" value="{{strtolower($action)}}"/>
                            <div class="form-group{{ $errors->has('name') ? ' has-error' : '' }}">
                                <label class="col-md-4 control-label">Name</label>
                                <div class="col-md-6">
                                    <input type="text" class="form-control" required name="name"
                                           value="{{ $item->name or '' }}">
                                    @if ($errors->has('name'))
                                        <span class="help-block">
                                        <strong>{{ $errors->first('name') }}</strong>
                                    </span>
                                    @endif
                                </div>
                            </div>
                            <div class="form-group{{ $errors->has('phone_number') ? ' has-error' : '' }}">
                                <label class="col-md-4 control-label">Phone Number</label>
                                <div class="col-md-6">
                                    <input type="tel" pattern="^\d{4}-\d{3}-\d{4}$" class="form-control" required
                                           name="phone_number"
                                           value="{{ $item->phone_number or '' }}"
                                           title="Phone Number (format: xxxx-xxx-xxxx)">
                                    @if ($errors->has('phone_number'))
                                        <span class="help-block">
                                        <strong>{{ $errors->first('phone_number') }}</strong>
                                    </span>
                                    @endif
                                </div>
                            </div>
                            <div class="form-group{{ $errors->has('password') ? ' has-error' : '' }}">
                                <label class="col-md-4 control-label">Additional Notes</label>
                                <div class="col-md-6">
                                    <textarea name="additional_notes"
                                              class="form-control">{{ $item->additional_notes or '' }}</textarea>
                                    @if ($errors->has('additional_notes'))
                                        <span class="help-block">
                                        <strong>{{ $errors->first('additional_notes') }}</strong>
                                    </span>
                                    @endif
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-6 col-md-offset-4">
                                    <button type="submit" class="btn btn-primary">
                                        <i class="fa fa-pencil"></i> {{ $action  }}
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection