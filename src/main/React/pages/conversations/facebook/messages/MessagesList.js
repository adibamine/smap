import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

import {connect} from 'react-redux'
import * as actionCreators from 'actions/conversations'

import MessagesTable from 'components/conversations/facebook/messages/MessagesTable'

class MessagesListCmp extends React.Component {
    constructor(props) {
        super(props);
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this);
    }

    componentDidMount(){
        this.props.loadConversationMessages({id: this.props.params.conversationId});
    }

    getConversationMessages(){
        if(!this.props.conversationMessages){
            return []
        }
        return this.props.conversationMessages
    }

    render() {
        return <div className="container-fluid">
            <div className="row">
                <div className="col-md-12">
                    <div className="panel panel-default">
                        <div className="panel-body">
                            <MessagesTable messages = {this.getConversationMessages()} sendMessage = {this.props.sendMessage} conversationId = {this.props.params.conversationId} />
                        </div>
                    </div>
                </div>
            </div>
        </div>

    }
};


function mapStateToProps(state) {
    return {
        conversationMessages: state.conversation.get('messages')
    }
}

export const MessagesList = connect(mapStateToProps, actionCreators)(MessagesListCmp)