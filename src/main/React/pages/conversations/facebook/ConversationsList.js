import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

import {connect} from 'react-redux'
import * as actionCreators from 'actions/conversations'

import ConversationsTable from 'components/conversations/facebook/ConversationsTable'

class ConversationsListCmp extends React.Component {
    constructor(props) {
        super(props);
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this);
    }

    componentDidMount(){
        this.props.loadConversations()
    }

    getConversations(){
        if(!this.props.conversations){
            return []
        }

        return this.props.conversations
    }

    render() {
        return <div className="container-fluid">
            <div className="row">
                <div className="col-md-12">
                    <div className="panel panel-default">
                        <div className="panel-body">
                            <ConversationsTable conversations = {this.getConversations()}/>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    }
};


function mapStateToProps(state) {
    return {
        conversations: state.conversation.get('conversations')
    }
}

export const ConversationsList = connect(mapStateToProps, actionCreators)(ConversationsListCmp)