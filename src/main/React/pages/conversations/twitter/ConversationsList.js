import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

import {connect} from 'react-redux'
import * as actionCreators from 'actions/conversations'

import TwitterConversationsTable from 'components/conversations/twitter/ConversationsTable'

class ConversationsListCmp extends React.Component {
    constructor(props) {
        super(props);
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this);
    }

    componentDidMount(){
        console.log("twitterconversation")
        this.props.loadTwitterConversations()
    }

    getConversations(){
        if(!this.props.twitterConversations){
            return []
        }

        return this.props.twitterConversations
    }

    render() {
        return <div className="container-fluid">
            <div className="row">
                <div className="col-md-12">
                    <div className="panel panel-default">
                        <div className="panel-body">
                            <TwitterConversationsTable twitterConversations = {this.getConversations()}/>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    }
};


function mapStateToProps(state) {
    return {
        twitterConversations: state.conversation.get('twitterConversations')
    }
}

export const TwitterConversationsList = connect(mapStateToProps, actionCreators)(ConversationsListCmp)