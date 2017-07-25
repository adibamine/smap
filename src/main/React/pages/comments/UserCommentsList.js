import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

import {connect} from 'react-redux'
import * as actionCreators from 'actions/comments'

import UserCommentsTable from 'components/comments/UserCommentsTable'

class UserCommentsListCmp extends React.Component {
    constructor(props) {
        super(props);
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this);
    }

    componentDidMount(){
        this.props.loadUserComments(this.props.params.client_id,this.props.params.user_id)
    }

    getUserComments(){
        if(!this.props.comments){
            return []
        }

        return this.props.comments
    }

    render() {
        return <div className="container-fluid">
            <div className="row">
                <div className="col-md-12">
                    <div className="panel panel-default">
                        <div className="panel-body">
                            <UserCommentsTable comments = {this.getUserComments()} block_user = {this.props.block_user} userId = {this.props.params.user_id} />
                        </div>
                    </div>
                </div>
            </div>
        </div>

    }
};


function mapStateToProps(state) {
    return {
        comments: state.comment.get('comments')
    }
}

export const UserCommentsList = connect(mapStateToProps, actionCreators)(UserCommentsListCmp)